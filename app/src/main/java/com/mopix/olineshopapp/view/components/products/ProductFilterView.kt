package com.mopix.olineshopapp.view.components.products

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mopix.olineshopapp.viewmodels.products.ProductViewModel

@Composable
fun ProductFilterView(viewModel: ProductViewModel = hiltViewModel()) {

    val filters = listOf("All", "New", "Popular")
    var selectedFilter by remember { mutableStateOf(0) }
    LazyRow {
        items(filters.size) { index ->
            TextButton(
                onClick = { selectedFilter = index
                          when(selectedFilter){
                           0 -> viewModel.getProducts(0,6){}
                              1 -> viewModel.getNewProducts {  }
                              2 -> viewModel.getPopularProducts {  }
                          }
                          },
                colors = ButtonDefaults.buttonColors(backgroundColor =
                if (selectedFilter == index) Color.LightGray
                else Color.Transparent),
                shape = RoundedCornerShape(20.dp), modifier = Modifier.width(90.dp),


                ) {
                Text(
                    text = filters[index],
                    color = if (isSystemInDarkTheme() && selectedFilter != index) Color.White else Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.width(10.dp))


        }

    }

}