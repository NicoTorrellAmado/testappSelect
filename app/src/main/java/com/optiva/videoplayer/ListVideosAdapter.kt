package com.optiva.videoplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.optiva.videoplayer.data.VideosEntity
import com.squareup.picasso.Picasso

class ListVideosAdapter(val context: Context, val list: List<VideosEntity>, val listener: OnItemClick):RecyclerView.Adapter<ListVideosAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.listTitletest)
        val imageview: ImageView = view.findViewById(R.id.imgVid)

        init {
            textView.setOnClickListener(this)
            imageview.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            listener.onItemClick(list[layoutPosition])
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.video_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView.text = list[position].name
        Picasso.get().load(list[position].thumb).into(viewHolder.imageview)

    }
    override fun getItemCount() = list.size

    interface OnItemClick{
        fun onItemClick(video: VideosEntity)
    }
}