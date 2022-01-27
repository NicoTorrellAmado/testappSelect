package com.optiva.videoplayer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoriesEntity(
    @PrimaryKey(autoGenerate = true) var udid:Int,
    @ColumnInfo(name = "id") var id:Int,
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "type") var type:String)
