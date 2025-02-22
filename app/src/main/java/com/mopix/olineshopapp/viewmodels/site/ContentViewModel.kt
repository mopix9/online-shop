package com.mopix.olineshopapp.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Content
import com.mopix.olineshopapp.models.repositories.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val repository: ContentRepository):ViewModel() {

    fun getContents(onSuccess : (response: ServiceResponse<Content>)-> Unit){
        viewModelScope.launch {
            val data = repository.getContents()
            onSuccess(data)
        }
    }
    fun getContentsById(title:String,onSuccess : (response: ServiceResponse<Content>)-> Unit){
        viewModelScope.launch {
            val data = repository.getContentsById(title)
            onSuccess(data)
        }
    }
}