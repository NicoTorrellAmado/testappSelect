package com.optiva.videoplayer.data

import com.google.gson.annotations.SerializedName

class VideosList(@SerializedName("videos") var videos:List<VideosEntity>) {
}