package com.mopix.olineshopapp.models.api.products

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {
    @GET("/api/color")
    suspend fun getProductColors():ServiceResponse<ProductColor>

    @GET("/api/color/{id}")
    suspend fun getColorsById(@Path("id") id:Long):ServiceResponse<ProductColor>
}