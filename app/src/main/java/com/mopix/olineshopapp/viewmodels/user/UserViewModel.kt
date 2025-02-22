package com.mopix.olineshopapp.viewmodels.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.user.User
import com.mopix.olineshopapp.models.user.UserVm
import com.mopix.olineshopapp.models.repositories.user.UserRepository
import com.mopix.olineshopapp.view.utiles.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository):ViewModel() {

    var token = ThisApp.token


    fun addUser(onSuccess : (response: ServiceResponse<User>)-> Unit){
        viewModelScope.launch {
            val data = repository.addUser(token)
            onSuccess(data)
        }
    }
    fun changepassword(user: UserVm, onSuccess : (response: ServiceResponse<User>)-> Unit){
        viewModelScope.launch {
            val data = repository.changepassword(user,token)
            onSuccess(data)
        }
    }
    fun getUserInfo( onSuccess : (response: ServiceResponse<User>)-> Unit){
        viewModelScope.launch {
            val data = repository.userInfo(token)
            onSuccess(data)
        }
    }
    fun login(user: UserVm, onSuccess : (response: ServiceResponse<UserVm>)-> Unit){
        viewModelScope.launch {
            val data = repository.login(user)
            onSuccess(data)
        }
    }
    fun register(user: UserVm, onSuccess : (response: ServiceResponse<User>)-> Unit){
        viewModelScope.launch {
            val data = repository.register(user)
            onSuccess(data)
        }
    }
    fun update(user: UserVm, onSuccess : (response: ServiceResponse<User>)-> Unit){
        viewModelScope.launch {
            val data = repository.update(user,token)
            onSuccess(data)
        }
    }
}