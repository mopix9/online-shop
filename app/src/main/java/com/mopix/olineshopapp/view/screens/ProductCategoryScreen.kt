package com.mopix.olineshopapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.view.components.loading
import com.mopix.olineshopapp.view.components.products.ProductListItemView
import com.mopix.olineshopapp.viewmodels.products.ProductByCategoryIdViewModel

@Composable
fun ProductCategoryScreen(
    categoryId: Long,
    title: String,
    navController: NavHostController,
    viewModel: ProductByCategoryIdViewModel = hiltViewModel()
) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }


    LazyColumn(Modifier.padding(20.dp, 0.dp)) {
        item {
            Text(text = title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(dataList.value.size) { index ->
            viewModel.getScrollPosition(index)
            if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) &&
                !viewModel.isLoading.value
            ) {
                viewModel.nextPage()
            }
            ProductListItemView(dataList.value[index], navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (isLoading.value) {
            item {
                loading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }


    }


}

