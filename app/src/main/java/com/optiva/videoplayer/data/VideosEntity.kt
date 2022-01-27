package com.optiva.videoplayer.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideosEntity(
    @PrimaryKey(autoGenerate = true) var udid:Int,
    @ColumnInfo(name = "id")var id:String,
    @ColumnInfo(name = "thumb") var thumb:String="",
    @ColumnInfo(name = "videoUrl") var videoUrl:String="",
    @ColumnInfo(name = "categoryId") var categoryId:Int=0,
    @ColumnInfo(name = "name") var name:String="")