package com.mopix.olineshopapp.models.api.site

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface SliderApi {
    @GET("/api/slider")
    suspend fun getSliders():ServiceResponse<Slider>

    @GET("/api/slider/{id}")
    suspend fun getSlidersById(@Path("id") id:Long):ServiceResponse<Slider>
}