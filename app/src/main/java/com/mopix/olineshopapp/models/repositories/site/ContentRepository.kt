package com.mopix.olineshopapp.models.repositories.site

import com.mopix.olineshopapp.models.api.site.ContentApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Content
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ContentRepository @Inject constructor(private val api: ContentApi) {
     suspend fun getContents(): ServiceResponse<Content> {
        return try {
            api.getContents()
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
    suspend fun getContentsById(title:String): ServiceResponse<Content> {
        return try {
            api.getContentsById(title)
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
}