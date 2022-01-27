package com.optiva.videoplayer

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import androidx.room.Room
import com.optiva.videoplayer.data.*
import com.optiva.videoplayer.network.GetData
import com.optiva.videoplayer.network.RetrofitConnect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) :  AndroidViewModel(application) {

    val dbcategories = Room.databaseBuilder(getApplication(), CategoriesDatabase::class.java,"categories.db").build()
    val dbvideo = Room.databaseBuilder(getApplication(), VideosDatabase::class.java,"videos.db").build()
    var categories: LiveData<List<CategoriesEntity>> = dbcategories.categoriesDAO().getALL()
    var videos: LiveData<List<VideosEntity>> = dbvideo.VideosDAO().getALL()

    fun getAllData(){
        val retrofitData = RetrofitConnect.retrofitInst?.create(GetData::class.java)
        val categoriesList = retrofitData?.getAll()

        categoriesList?.enqueue(object: Callback<DataList> {
            override fun onResponse(
                call: Call<DataList>,
                response: Response<DataList>
            ) {
                val test = response?.body()
                val cat = test?.categories
                val vid = test?.videos

                viewModelScope.launch(Dispatchers.IO) {
                    if (cat != null) {
                        for(c in cat){
                            insertorupdateCategory(CategoriesEntity(0,c.id,c.title,c.type))
                        }
                    }
                    if (vid != null) {
                        for(v in vid){
                            insertorupdateVideo(VideosEntity(0,v.id,v.thumb,v.videoUrl,v.categoryId,v.name))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<DataList>, t: Throwable) {
                Toast.makeText(getApplication(),"Error", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun insertorupdateCategory(categories: CategoriesEntity){
        dbcategories.runInTransaction{
            val cat = dbcategories.categoriesDAO().getitemsId(categories.id)
            if(cat.size<=0){
                dbcategories.categoriesDAO().insertAll(categories)
            }else{
               dbcategories.categoriesDAO().update(categories)
            }
        }
    }

    fun insertorupdateVideo(videos: VideosEntity){
        dbvideo.runInTransaction{
            val vid = dbvideo.VideosDAO().getitemsbyId(videos.id)
            if(vid.size<=0){
                dbvideo.VideosDAO().insertAll(videos)
            }else{
                dbvideo.VideosDAO().update(videos)
            }
        }
    }

    fun getVideosbyCatId(id:Int){
        videos = dbvideo.VideosDAO().getitemsbycatId(id)
    }
}