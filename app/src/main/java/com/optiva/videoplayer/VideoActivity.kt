package com.optiva.videoplayer

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.VideoView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.isVisible
import com.optiva.videoplayer.data.VideosEntity


class VideoActivity : AppCompatActivity(),ListVideosAdapter.OnItemClick {

    lateinit var rec:RecyclerView
    lateinit var listv:ListView
    lateinit var vide:VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_layout)
        val nav = supportActionBar
        if (nav != null) {
            nav.setDisplayHomeAsUpEnabled(true)
        }
        rec = findViewById(R.id.videoListid) as RecyclerView
        listv = findViewById(R.id.VidVListid) as ListView
        vide = findViewById(R.id.videFrag) as VideoView
        vide.isFocusableInTouchMode=false
        val catId:Int = intent.getIntExtra("cat",0)
        val type: String? = intent.getStringExtra("type")
        if(type =="LANDSCAPE"){
            rec.visibility = View.VISIBLE
            listv.visibility= View.GONE
            var layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            rec.layoutManager = layoutManager
        }else{
            rec.visibility = View.GONE
            listv.visibility= View.VISIBLE
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        val dataviewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        dataviewModel.getVideosbyCatId(catId)

        dataviewModel.videos.observe(this, Observer{ videos ->
            if(videos.size>0){
                if(rec.isVisible) {
                    val adapter = ListVideosAdapter(this, videos,this)
                    rec.adapter = adapter

                }
                if(listv.isVisible){
                    val adapter = ListVideoPorAdapter(applicationContext, videos)
                    listv.adapter=adapter
                    listv.onItemClickListener = object : AdapterView.OnItemClickListener{
                        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            val vid:Uri = Uri.parse(videos.get(p2).videoUrl)
                            vide.setVideoURI(vid)
                            vide.requestFocus()
                            vide.visibility = View.VISIBLE
                            vide.start()

                        }
                    }
                }

            }

        })
        vide.setOnClickListener{
            vide.stopPlayback()
            vide.visibility= View.GONE
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClick(video: VideosEntity) {
        val vid:Uri = Uri.parse(video.videoUrl)
        vide.setVideoURI(vid)
        vide.requestFocus()
        vide.visibility = View.VISIBLE
        vide.start()
    }

}