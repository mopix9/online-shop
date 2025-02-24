package com.mopix.olineshopapp.view.components.products

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mopix.olineshopapp.view.components.loading
import com.mopix.olineshopapp.viewmodels.products.ProductCategoryViewModel

@Composable
fun ProductCategoryListView(navController: NavController,viewModel: ProductCategoryViewModel = hiltViewModel()) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading)}
    
    if (isLoading.value)
        loading(modifier = Modifier.width(160.dp).height(200.dp),2)
    else {

        LazyRow {
            items(dataList.value.size) { index ->
                ProductCategoryItemView(dataList.value[index],navController)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}
