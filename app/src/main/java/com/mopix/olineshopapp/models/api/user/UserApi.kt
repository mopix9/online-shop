package com.mopix.olineshopapp.models.api.user

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.user.User
import com.mopix.olineshopapp.models.user.UserVm
import retrofit2.http.*

interface UserApi {

    @PUT("/api/user/changepassword")
    suspend fun changepassword(
        @Body User: UserVm,
        @Header("Authorization") Token: String
    ): ServiceResponse<User>

    @GET("/api/user")
    suspend fun getUseInfo(
        @Header("Authorization") Token: String
    ): ServiceResponse<User>

    @POST("/api/user/login")
    suspend fun login(
        @Body User: UserVm
    ): ServiceResponse<UserVm>

    @POST("/api/user/register")
    suspend fun register(
        @Body User: UserVm
    ): ServiceResponse<User>

    @PUT("/api/user/update")
    suspend fun update(
        @Body User: UserVm,
        @Header("Authorization") Token: String
    ): ServiceResponse<User>

}

