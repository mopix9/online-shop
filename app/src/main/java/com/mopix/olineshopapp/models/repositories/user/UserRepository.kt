package com.mopix.olineshopapp.models.repositories.user

import com.mopix.olineshopapp.models.api.user.UserApi
import com.mopix.olineshopapp.models.base.BaseRepository
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.user.User
import com.mopix.olineshopapp.models.user.UserVm
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val api: UserApi): BaseRepository() {

     suspend fun addUser(token:String): ServiceResponse<User> {
        return try {
            api.getUseInfo(prepareToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun changepassword(user:UserVm,token: String): ServiceResponse<User> {
        return try {
            api.changepassword(user,prepareToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun userInfo(token: String): ServiceResponse<User> {
        return try {
            api.getUseInfo(prepareToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun login(user:UserVm): ServiceResponse<UserVm> {
        return try {
            api.login(user)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun register(user:UserVm): ServiceResponse<User> {
        return try {
            api.register(user)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun update(user:UserVm,token: String): ServiceResponse<User> {
        return try {
            api.update(user,prepareToken(token))
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}
