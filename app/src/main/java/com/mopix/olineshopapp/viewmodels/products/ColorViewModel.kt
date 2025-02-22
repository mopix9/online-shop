package com.mopix.olineshopapp.viewmodels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.products.ProductColor
import com.mopix.olineshopapp.models.repositories.products.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(private val repository: ColorRepository):ViewModel() {

    fun getColors(onSuccess : (response: ServiceResponse<ProductColor>)-> Unit){
        viewModelScope.launch {
            val data = repository.getColors()
            onSuccess(data)
        }
    }
    fun getColorsById(id:Long,onSuccess : (response: ServiceResponse<ProductColor>)-> Unit){
        viewModelScope.launch {
            val data = repository.getColorsById(id)
            onSuccess(data)
        }
    }
}