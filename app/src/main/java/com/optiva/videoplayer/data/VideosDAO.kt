package com.optiva.videoplayer.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideosDAO {


    @Query("SELECT * FROM videos")
    fun getALL(): LiveData<List<VideosEntity>>

    @Insert
    fun insertAll(vararg todo: VideosEntity)

    @Query("SELECT * FROM videos WHERE id=:id")
    fun getitemsbyId(id: String):List<VideosEntity>

    @Query("SELECT * FROM videos WHERE categoryId=:id")
    fun getitemsbycatId(id: Int):LiveData<List<VideosEntity>>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(category:VideosEntity)
}