package com.mopix.olineshopapp.models.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mopix.olineshopapp.models.db.models.BasketEntity

@Dao
interface BasketEntityDao {

    @Insert
    fun add(basketEntity: BasketEntity)

    @Update
    fun update(basketEntity: BasketEntity)

    @Delete
    fun delete(basketEntity: BasketEntity)

    @Query("select * from BasketEntity")
    fun getAll(): List<BasketEntity>

    @Query("select * from BasketEntity")
    fun getAllLIVE(): LiveData<List<BasketEntity>>

    @Query("delete from BasketEntity")
    fun deleteAll()
}