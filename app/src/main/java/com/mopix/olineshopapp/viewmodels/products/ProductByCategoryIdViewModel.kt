package com.mopix.olineshopapp.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.Product
import com.mopix.olineshopapp.models.repositories.products.ProductRepository
import com.mopix.olineshopapp.view.utiles.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductByCategoryIdViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {


    var categoryId: Long = ThisApp.productCategoryId
    var pageIndex = mutableStateOf(0)
    var pageSize = 3
    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)
    var scrollPosition = 0

    init {
        getProductsByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }


    fun getProductsByCategoryId(
        categoryId: Long,
        pageIndex: Int,
        pageSize: Int,
        onSuccess: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            val response = repository.getProductsByCategoryId(categoryId, pageIndex, pageSize)

            onSuccess(response)
        }
    }

    fun incrementalPage() {
        pageIndex.value = pageIndex.value + 1

    }

    fun getScrollPosition(position: Int) {
        scrollPosition = position
    }

    fun appenedItem(items: List<Product>) {
        val current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current
    }


    fun nextPage() {
        viewModelScope.launch {

            if (scrollPosition + 1 >= pageIndex.value * pageSize) {
                isLoading.value = true
                incrementalPage()
//delay endakhtam ke motanaheh beshom page index jadid load mishavad dar poroje asli var midarim
                delay(4000)
                if (pageIndex.value > 0) {
                    getProductsByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty()) {
                            appenedItem(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}
