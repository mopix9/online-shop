package com.mopix.olineshopapp.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.Product
import com.mopix.olineshopapp.models.repositories.products.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)
    var data = mutableStateOf<Product?>(null)

    init {
        getProducts (0,20){ response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProducts(
        pageIndex: Int,
        pageSize: Int,
        onSuccess: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getProduct(pageIndex,pageSize)
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onSuccess(response)
        }
    }
  fun getProductsByCategoryId(categoryId:Long,
        pageIndex: Int,
        pageSize: Int,
        onSuccess: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getProductsByCategoryId(categoryId,pageIndex,pageSize)

            onSuccess(response)
        }
    }

    fun getProductsById(id: Long, onSuccess: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getProductById(id)
            onSuccess(response)
        }
    }

    fun getNewProducts(onSuccess: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getNewProduct()
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onSuccess(response)
        }
    }

    fun getPopularProducts(onSuccess: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getPopularProduct()
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onSuccess(response)
        }
    }
}