package com.mopix.olineshopapp.view.screens

import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.mopix.olineshopapp.view.activity.MainActivity
import com.mopix.olineshopapp.models.db.viewmodels.BasketEntityViewModel
import com.mopix.olineshopapp.models.db.viewmodels.UserEntityViewModel
import com.mopix.olineshopapp.view.utiles.ThisApp


@Composable
@SuppressWarnings("UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("UnusedMaterialScaffoldPaddingParameter")
fun MainScreen(mainActivity: MainActivity) {
    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    var showHomeScreen by remember { mutableStateOf(false)}

    val basketViewModel =
        ViewModelProvider(mainActivity).get(BasketEntityViewModel::class.java)

    val userEntityViewModel =
        ViewModelProvider(mainActivity).get(UserEntityViewModel::class.java)

    userEntityViewModel.getCurrentUser().observe(mainActivity){observed ->
        userEntityViewModel.currentUser.value = observed

    }

    basketViewModel.getAllBasketLive().observe(mainActivity) { observed ->
        basketViewModel.dataList.value = observed
    }
    Scaffold(
        topBar = {
            if (!fullScreen)
                TopAppView(navController, basketViewModel,userEntityViewModel,showHomeScreen)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                fullScreen = false
                HomeScreen(navController)
            }
            composable("basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)
            }

            composable("UserPayment") {
                fullScreen = true
                UserPaymentScreen(navController, basketViewModel,mainActivity,userEntityViewModel)
            }
            composable("products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )) { backStack ->
                fullScreen = false
                val id = backStack.arguments?.getLong("categoryId")
                val title = backStack.arguments?.getString("title")
                ThisApp.productCategoryId = id!!
                ProductCategoryScreen(id!!, title!!, navController)

            }
            composable(
                "showProduct/{productId}",
                arguments = listOf(
                    navArgument("productId") { type = NavType.LongType }
                )
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getLong("productId").let {
                    ShowProductScreen(it!!, navController, basketViewModel)
                }
            }
            composable(
                "invoice/{id}",
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://onlineshopmopix.ir/{id}" }),
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType })
            ) { backStackEntry ->

                if (userEntityViewModel.currentUser.value !=null) {
                    ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    ThisApp.invoiceId = backStackEntry.arguments?.getLong("id")!!
                }

                InvoiceScreen(navController, backStackEntry.arguments?.getLong("id")!!)
            }
            composable("login") {
                fullScreen = true
                LoginScreen(navController, userEntityViewModel)
            }
            composable("dashboard") {
                fullScreen = true
                showHomeScreen = false
                ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                DashboardScreen(navController, userEntityViewModel)
            }
            composable("invoices") {
                fullScreen = true
                showHomeScreen = false
                InvoiceListScreen(navController)
            }
            composable("editProfile") {
                fullScreen = true
                showHomeScreen = false
                EditProfileScreen(navController,userEntityViewModel)
            }
            composable("changePassword") {
                fullScreen = true
                showHomeScreen = false
                ChangePasswordScreen(navController,userEntityViewModel)
            }


        }
    }
}