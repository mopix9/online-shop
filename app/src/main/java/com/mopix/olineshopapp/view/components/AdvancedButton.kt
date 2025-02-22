package com.mopix.olineshopapp.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvancedButton(
    title:String,
    subtitle:String,
    imageVector: ImageVector,
    iconBackgroundColor:Color,
    onClick: () -> Unit
) {
    Card(onClick = { onClick() }, elevation = 0.dp) {
        Row(Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier.padding(5.dp),
                shape = RoundedCornerShape(50.dp),
                backgroundColor = iconBackgroundColor
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "",
                    Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    tint = Color.White
                )
            }
            Spacer(
                modifier = Modifier
                    .width(15.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    color = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                )
            }
        }
    }
}