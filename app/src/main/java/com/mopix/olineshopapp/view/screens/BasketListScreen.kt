package com.mopix.olineshopapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.models.db.viewmodels.BasketEntityViewModel
import com.mopix.olineshopapp.view.components.BasketItemView
import com.mopix.olineshopapp.view.theme.Dark

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {
    var dataList by remember { mutableStateOf(basketViewModel.dataList) }
//    TODO FIXME Total price
    var totalPrice by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.padding(15.dp)) {
        item {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(5.dp))

                Column {
                    Text(
                        text = "Shopping Cart", textAlign = TextAlign.Center,
                        fontSize = 20.sp, modifier = Modifier.padding(0.dp,if (dataList.value.isNotEmpty()) 0.dp else 9.dp,0.dp,0.dp))

                    Spacer(modifier = Modifier.height(5.dp))
                    if (dataList.value.isNotEmpty()) {
                        Text(
                            text = "${dataList.value.size} items", textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                }


            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            if (dataList.value.isEmpty()) {
                Column(

                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    Text(text = "Your Shopping Cart is empty!", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(40.dp))
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "",
                        Modifier.size(250.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(40.dp))

                }
            }
        }

            items(dataList.value.size) { index ->
                BasketItemView(dataList.value[index], basketViewModel, navController)
            }
        item {
            if (dataList.value.isNotEmpty()) {

                Text(text ="$totalPrice", color = Color.Gray, fontSize = 22.sp)


                Button(
                    onClick = { navController.navigate("UserPayment") },
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(15.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        Dark
                    )
                ) {
                    Text(text = "Proceed To Payment", fontSize = 20.sp, color = Color.White)


                }
            }

        }

    }


}
