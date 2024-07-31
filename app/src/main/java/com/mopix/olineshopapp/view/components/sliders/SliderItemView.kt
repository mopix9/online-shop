package com.mopix.olineshopapp.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mopix.olineshopapp.models.site.Slider
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderItemView(slider: Slider) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp), clip = true),
        shape = RoundedCornerShape(20.dp),
    ) {


        Box() {
            GlideImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                imageModel = slider.image!!,
                loading = {
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CircularProgressIndicator()

                    }                },
                // shows an error text message when request failed.
                failure = {
                    Text(text = "image request failed.")
                })
            Box(Modifier.padding(8.dp).fillMaxSize(), contentAlignment = Alignment.BottomStart) {
                Column() {
                    Text(
                        text = slider.title!!, fontSize = 15.sp, color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = slider.subTitle!!, fontSize = 15.sp, color = Color.LightGray)

                }
            }
        }
    }

}
