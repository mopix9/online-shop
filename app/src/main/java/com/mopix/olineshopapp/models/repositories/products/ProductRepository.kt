package com.mopix.olineshopapp.models.repositories.products

import com.mopix.olineshopapp.models.api.products.ProductControllerApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.Product
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepository @Inject constructor(private val api: ProductControllerApi) {
     suspend fun getProduct( pageIndex: Int,pageSize: Int): ServiceResponse<Product> {
        return try {
            api.getProduct( pageIndex,pageSize)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getProductsByCategoryId( categoryId:Long,pageIndex: Int,pageSize: Int): ServiceResponse<Product> {
        return try {
            api.getProductsByCategoryId( categoryId,pageIndex,pageSize)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getProductById(id: Long): ServiceResponse<Product> {
        return try {
            api.getProductById(id)
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getNewProduct(): ServiceResponse<Product> {
        return try {
            api.getNewProduct()
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
    suspend fun getPopularProduct(): ServiceResponse<Product> {
        return try {
            api.getPopularProduct()
        }catch (e:Exception){
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}