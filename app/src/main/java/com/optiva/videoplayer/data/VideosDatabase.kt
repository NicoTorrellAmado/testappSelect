package com.optiva.videoplayer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(VideosEntity::class),version=1)
abstract class VideosDatabase : RoomDatabase() {
    abstract fun VideosDAO(): VideosDAO
}
