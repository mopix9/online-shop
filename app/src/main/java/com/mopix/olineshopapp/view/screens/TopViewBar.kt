package com.mopix.olineshopapp.view.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.models.db.viewmodels.BasketEntityViewModel
import com.mopix.olineshopapp.models.db.viewmodels.UserEntityViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopAppView(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    showHomeScreen: Boolean,) {
    val animationVisibility =
        remember { MutableTransitionState(false) }.apply { targetState = true }


    TopAppBar(
        title = {
            AnimatedVisibility(visibleState = animationVisibility,
                enter = slideInVertically(
                    animationSpec = tween(500,0),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500,0),

                    ),
                exit = fadeOut()) {


                Text(text = "online shop")
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription ="" )

            }
            AnimatedVisibility(visibleState = animationVisibility,
                enter = slideInVertically(
                    animationSpec = tween(500,500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500,500),

                    ),
                exit = fadeOut()) {


                IconButton(onClick = {
                    if (userEntityViewModel.currentUser.value == null) {
                        navController.navigate("login")
                    } else navController.navigate("dashboard")

                }) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                }
            }
            AnimatedVisibility(visibleState = animationVisibility,
                enter = slideInVertically(
                    animationSpec = tween(500,1000),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500,1000),

                    ),
                exit = fadeOut()) {


                IconButton(onClick = { navController.navigate("basket") }) {

                    Box(contentAlignment = Alignment.BottomEnd) {

                        Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "")

                        if (basketViewModel.dataList.value.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(50.dp),
                                backgroundColor = Color.Red,
                                elevation = 0.dp,
                                modifier = Modifier.size(14.dp)
                            ) {
                                Text(
                                    text = "${basketViewModel.dataList.value.size}",
                                    fontSize = 8.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(1.dp), textAlign = TextAlign.Center
                                )

                            }
                        }
                    }

                }
            }
        }
    )

}