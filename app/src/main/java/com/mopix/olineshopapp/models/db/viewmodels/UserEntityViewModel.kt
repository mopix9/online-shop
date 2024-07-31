package com.mopix.olineshopapp.models.db.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mopix.olineshopapp.models.db.repositories.UserEntityRepository
import com.mopix.olineshopapp.models.db.models.UserEntity

class UserEntityViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = UserEntityRepository(application)
    var currentUser = mutableStateOf<UserEntity?>(null)


    suspend fun insert(user: UserEntity) {
        deleteAll()
        repository.insert(user)
    }

    suspend fun update(user: UserEntity) {
        if (user.id <= 0) return
        repository.update(user)
    }

    suspend fun delete(user: UserEntity) {
        if (user.id <= 0) return
        repository.delete(user)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return repository.getCurrentUser()
    }
}