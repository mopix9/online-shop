package com.mopix.olineshopapp.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.site.Blog
import com.mopix.olineshopapp.models.repositories.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: BlogRepository):ViewModel() {

    fun getBlogs(onSuccess : (response: ServiceResponse<Blog>)-> Unit){
        viewModelScope.launch {
            val data = repository.getBlogs()
            onSuccess(data)
        }
    }
    fun getBlogsById(id:Long,onSuccess : (response: ServiceResponse<Blog>)-> Unit){
        viewModelScope.launch {
            val data = repository.getBlogsById(id)
            onSuccess(data)
        }
    }
}