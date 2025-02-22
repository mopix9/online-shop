package com.mopix.olineshopapp.models.api.products

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductControllerApi {
    @GET("/api/product")
    suspend fun getProduct(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
    ): ServiceResponse<Product>

    @GET("/api/product/cat/{id}")
    suspend fun getProductsByCategoryId(
        @Path ("id") id:Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
    ): ServiceResponse<Product>


    @GET("/api/product/{id}")
    suspend fun getProductById(@Path("id") id: Long): ServiceResponse<Product>

    @GET("/api/product/new")
    suspend fun getNewProduct(): ServiceResponse<Product>

    @GET("/api/product/popular")
    suspend fun getPopularProduct(): ServiceResponse<Product>
}