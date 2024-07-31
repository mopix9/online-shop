package com.mopix.olineshopapp.models.db.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.mopix.olineshopapp.models.db.OnlineShopDataBase
import com.mopix.olineshopapp.models.db.dao.UserEntityDao
import com.mopix.olineshopapp.models.db.models.UserEntity

class UserEntityRepository(application: Application) {

    private var userDao: UserEntityDao
    private lateinit var currentUserEntity: LiveData<UserEntity>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        userDao = database.userDao()
        currentUserEntity = userDao.get()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUserEntity
    }

    suspend fun insert(user: UserEntity) {
        deleteAll()
        userDao.add(user)
    }

    suspend fun update(user: UserEntity) {
        userDao.update(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.delete(user)
    }

    suspend fun deleteAll() {
        return userDao.deleteAll()
    }

}