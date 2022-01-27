package com.optiva.videoplayer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CategoriesEntity::class),version=1)
abstract class CategoriesDatabase : RoomDatabase(){
    abstract fun categoriesDAO(): CAtegoryDAO
}