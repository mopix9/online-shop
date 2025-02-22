package com.mopix.olineshopapp.models.repositories.site

import com.mopix.olineshopapp.models.api.site.BlogApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Blog
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BlogRepository @Inject constructor(private val api: BlogApi) {
     suspend fun getBlogs(): ServiceResponse<Blog> {
        return try {
            api.getBlogs()
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
    suspend fun getBlogsById(id:Long): ServiceResponse<Blog> {
        return try {
            api.getBlogsById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
}