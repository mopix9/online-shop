package com.mopix.olineshopapp.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.ProductCategory
import com.mopix.olineshopapp.models.repositories.products.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(private val repository: ProductCategoryRepository):ViewModel() {

    var dataList = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getProductCategorys { response ->
            isLoading.value =false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }


    fun getProductCategorys(onSuccess : (response: ServiceResponse<ProductCategory>)-> Unit){
        viewModelScope.launch {
            val data = repository.getCategory()
            onSuccess(data)
        }
    }
    fun getProductCategorysById(id:Long,onSuccess : (response: ServiceResponse<ProductCategory>)-> Unit){
        viewModelScope.launch {
            val data = repository.getCategoryById(id)
            onSuccess(data)
        }
    }
}