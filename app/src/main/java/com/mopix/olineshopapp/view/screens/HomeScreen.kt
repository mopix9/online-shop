package com.mopix.olineshopapp.view.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mopix.olineshopapp.view.components.sliders.SliderListView
import com.mopix.olineshopapp.view.components.loading
import com.mopix.olineshopapp.view.components.products.ProductCategoryListView
import com.mopix.olineshopapp.view.components.products.ProductFilterView
import com.mopix.olineshopapp.view.components.products.ProductListItemView
import com.mopix.olineshopapp.viewmodels.products.ProductViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    var productList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }
    val animationVisibility =
        remember { MutableTransitionState(false) }.apply { targetState = true }


    /*
        1) Slider
        2) Product Category
        3) (New, Popular) -> Filter
        4) Products (New,Popular)
     */

    LazyColumn(Modifier.padding(20.dp, 0.dp)) {
        item {
            AnimatedVisibility(visibleState = animationVisibility,
                enter = slideInVertically(
                    animationSpec = tween(500,500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500,500),

                    ),
                exit = fadeOut()) {


                SliderListView()
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            AnimatedVisibility(visibleState = animationVisibility,
                enter = slideInVertically(
                    animationSpec = tween(500,1000),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500,1000),

                    ),
                exit = fadeOut()) {

                ProductCategoryListView(navController)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            AnimatedVisibility(visibleState = animationVisibility,
                enter = slideInVertically(
                    animationSpec = tween(500,1500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500,1500),

                    ),
                exit = fadeOut()) {

                ProductFilterView()
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
        if (isLoading.value) {
            item {

                loading(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp)
                )
            }
        } else {


            items(productList.value.size) { index ->
                AnimatedVisibility(visibleState = animationVisibility,
                    enter = slideInVertically(
                        animationSpec = tween(500,2000),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500,2000),

                        ),
                    exit = fadeOut()) {

                    ProductListItemView(productList.value[index], navController)
                }
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }


}

