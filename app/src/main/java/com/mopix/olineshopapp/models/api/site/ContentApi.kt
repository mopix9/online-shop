package com.mopix.olineshopapp.models.api.site

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {
    @GET("/api/content")
    suspend fun getContents():ServiceResponse<Content>

    @GET("/api/content/{title}")
    suspend fun getContentsById(@Path("title") title:String):ServiceResponse<Content>
}