package com.mopix.olineshopapp.view.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.models.db.models.BasketEntity
import com.mopix.olineshopapp.models.db.viewmodels.BasketEntityViewModel
import com.mopix.olineshopapp.view.components.loading
import com.mopix.olineshopapp.viewmodels.products.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ShowProductScreen(
    productId: Long,
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val data by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }
    val animationVisibility =
        remember { MutableTransitionState(false) }.apply { targetState = true }

    val context = LocalContext.current

    viewModel.getProductsById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data!!.isNotEmpty()) {
                viewModel.data.value = response.data!![0]
            } else {
                Toast.makeText(context, "Error on load data!", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
        }
    }

    if (isLoading) {
        loading(modifier = Modifier.fillMaxSize())
    } else {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(0.dp),
        ) {

            Box {

                GlideImage(

                    imageModel = data.value!!.image!!,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    })
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    AnimatedVisibility(visibleState = animationVisibility,
                        enter = slideInVertically(
                            animationSpec = tween(500,500),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500,500),

                            ),
                        exit = fadeOut()) {

                        Text(
                            text = data.value!!.title!!,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(visibleState = animationVisibility,
                        enter = slideInVertically(
                            animationSpec = tween(500,800),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500,800),

                            ),
                        exit = fadeOut()) {

                        Text(
                            text = "${data.value!!.price!!}T",
                            color = Color.White,
                            fontSize = 26.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(visibleState = animationVisibility,
                        enter = slideInVertically(
                            animationSpec = tween(500,1000),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500,1000),

                            ),
                        exit = fadeOut()) {

                        Text(
                            text = "Sizes",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow {
                        items(data.value!!.sizes!!.size) { index ->
                            AnimatedVisibility(visibleState = animationVisibility,
                                enter = slideInVertically(
                                    animationSpec = tween(500, 1200),
                                    initialOffsetY = { -40 }
                                ) + fadeIn(
                                    animationSpec = tween(500, 1200),

                                    ),
                                exit = fadeOut()) {

                                TextButton(
                                    onClick = { selectedSize = index },
                                    shape = RoundedCornerShape(15.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor =
                                        if (selectedSize == index) Color.White else Color.Transparent
                                    ),
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Text(
                                        text = data.value!!.sizes!![index].title!!,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedSize == index) Color.Black else Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(visibleState = animationVisibility,
                        enter = slideInVertically(
                            animationSpec = tween(500,1500),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500,1500),

                            ),
                        exit = fadeOut()) {

                        Text(
                            text = "Colors",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow {
                        items(data.value!!.colors!!.size) { index ->
                            AnimatedVisibility(visibleState = animationVisibility,
                                enter = slideInVertically(
                                    animationSpec = tween(500, 1800),
                                    initialOffsetY = { -40 }
                                ) + fadeIn(
                                    animationSpec = tween(500, 1800),

                                    ),
                                exit = fadeOut()) {

                                TextButton(
                                    onClick = { selectedColor = index },
                                    shape = RoundedCornerShape(50.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor =
                                        Color(android.graphics.Color.parseColor("#${data.value!!.colors!![index].hexValue}"))
                                    ),
                                    modifier = Modifier.size(40.dp),
                                    border = BorderStroke(1.dp, Color.White)
                                ) {
                                    if (selectedColor == index) {
                                        Icon(
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = "",
                                            tint = if (data.value!!.colors!![index].hexValue?.toLowerCase(
                                                    Locale.ROOT
                                                ) == "ffffff"
                                            ) Color.Black else Color.White
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    AnimatedVisibility(visibleState = animationVisibility,
                        enter = slideInVertically(
                            animationSpec = tween(500,2000),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500,2000),

                            ),
                        exit = fadeOut()) {

                        Button(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val basket = BasketEntity(
                                        productId = productId,
                                        quantity = 1,
                                        sizeId = data.value!!.sizes!![selectedSize].id!!,
                                        colorId = data.value!!.colors!![selectedColor].id!!,
                                        image = data.value!!.image,
                                        price = data.value!!.price!!,
                                        title = data.value!!.title,
                                        colorHex = data.value!!.colors!![selectedColor].hexValue!!,
                                        size = data.value!!.sizes!![selectedSize].title!!
                                    )
                                    basketViewModel.addToBasket(basket)
                                }
                                Toast.makeText(
                                    context,
                                    "Product added to your basket!",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.popBackStack()
                            },
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Buy Now",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

}