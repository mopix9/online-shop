package com.mopix.olineshopapp.models.repositories.products

import com.mopix.olineshopapp.models.api.products.ProductCategoryApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {
     suspend fun getCategory(): ServiceResponse<ProductCategory> {
        return try {
            api.getCategory()
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
    suspend fun getCategoryById(id:Long): ServiceResponse<ProductCategory> {
        return try {
            api.getCategoryById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }
}