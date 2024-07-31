package com.mopix.olineshopapp.models.repositories.site

import com.mopix.olineshopapp.models.api.site.SliderApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SliderRepository @Inject constructor(private val api: SliderApi) {
     suspend fun getSliders(): ServiceResponse<Slider> {
        return try {
            api.getSliders()
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
    suspend fun getSlidersById(id:Long): ServiceResponse<Slider> {

        return try {
            api.getSlidersById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
}