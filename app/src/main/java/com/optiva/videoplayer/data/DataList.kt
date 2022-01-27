package com.optiva.videoplayer.data

import com.google.gson.annotations.SerializedName

class DataList(@SerializedName("categories") var categories:List<CategoriesEntity>, @SerializedName("videos") var videos:List<VideosEntity>) {
}