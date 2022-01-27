package com.optiva.videoplayer.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CAtegoryDAO {

    @Query("SELECT * FROM categories")
    fun getALL():LiveData<List<CategoriesEntity>>

    @Query("SELECT * FROM categories WHERE id=:idEnt")
    fun getitemsId(idEnt: Int):List<CategoriesEntity>

    @Insert
    fun insertAll(vararg todo: CategoriesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(category:CategoriesEntity)
}