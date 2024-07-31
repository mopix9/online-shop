package com.mopix.olineshopapp.models.api.site

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Blog
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {
    @GET("/api/blog")
    suspend fun getBlogs():ServiceResponse<Blog>

    @GET("/api/blog/{id}")
    suspend fun getBlogsById(@Path("id") id:Long):ServiceResponse<Blog>
}