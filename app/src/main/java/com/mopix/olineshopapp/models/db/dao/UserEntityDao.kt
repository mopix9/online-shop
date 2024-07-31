package com.mopix.olineshopapp.models.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mopix.olineshopapp.models.db.models.UserEntity

@Dao
interface UserEntityDao {

    @Insert
    fun add(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("select * from UserEntity limit 1")
    fun get(): LiveData<UserEntity>

    @Query("delete from UserEntity")
    fun deleteAll()
}