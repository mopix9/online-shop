package com.mopix.olineshopapp.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.models.db.models.BasketEntity
import com.mopix.olineshopapp.models.db.viewmodels.BasketEntityViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketItemView(
    basketEntity: BasketEntity,
    viewModel: BasketEntityViewModel,
    navController: NavHostController
) {
    var quantity by remember { mutableStateOf(basketEntity.quantity) }
    Row(Modifier.fillMaxWidth()) {

        Card(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(20.dp), onClick = {
                navController.navigate("showProduct/${basketEntity.productId}")
            }
        ) {
            GlideImage(
                imageModel = basketEntity.image!!,
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

        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(

                text = basketEntity.title!!,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "${basketEntity.price!!}T", color = Color.Gray, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(5.dp))

            Row {
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.decrementalQuantity(basketEntity)
                    }
                    quantity--
                }, Modifier.size(30.dp)) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = quantity.toString(), fontSize = 20.sp)
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.incrementalQuantity(basketEntity)
                    }
                    quantity++

                }, Modifier.size(30.dp)) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.delete(basketEntity)
                    }
                }, Modifier.size(25.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Row(modifier = Modifier.padding(5.dp)) {


            Card(
                shape = RoundedCornerShape(50.dp),

                backgroundColor =
                Color(android.graphics.Color.parseColor("#${basketEntity.colorHex}")),
                modifier = Modifier
                    .size(30.dp)
                    .align(CenterVertically),
                border = BorderStroke(1.dp, androidx.compose.ui.graphics.Color.White),
                content = {}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "size : ${basketEntity.size!!}", fontSize = 15.sp, color = Color.Gray)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}