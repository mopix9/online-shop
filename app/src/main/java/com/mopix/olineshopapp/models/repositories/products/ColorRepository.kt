package com.mopix.olineshopapp.models.repositories.products

import com.mopix.olineshopapp.models.api.products.ColorApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val api: ColorApi) {
     suspend fun getColors(): ServiceResponse<ProductColor> {
        return try {
            api.getProductColors()
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
    suspend fun getColorsById(id:Long): ServiceResponse<ProductColor> {
        return try {
            api.getColorsById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
}