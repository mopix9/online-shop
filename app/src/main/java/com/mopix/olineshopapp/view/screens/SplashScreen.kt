package com.mopix.olineshopapp.view.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mopix.olineshopapp.view.activity.MainActivity
import com.mopix.olineshopapp.view.activity.SplashActivity
import com.mopix.olineshopapp.models.db.viewmodels.UserEntityViewModel
import com.mopix.olineshopapp.viewmodels.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun SplashScreen(
    splashActivity: SplashActivity,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var isLoading by remember { mutableStateOf(false) }

    if (!isLoading){
    userViewModel.getUserInfo { response ->

        if (response.status != "OK") {
            if (response.message!!.lowercase(Locale.ROOT).startsWith("failed to connect to")) {
                Toast.makeText(splashActivity, "can not connect to server", Toast.LENGTH_LONG).show()
                return@getUserInfo
            } else if (response.message!!.lowercase(Locale.ROOT).startsWith("http 417")) {
                CoroutineScope(Dispatchers.IO).launch {
                    userEntityViewModel.deleteAll()
                }
            }
            }
            val intent = Intent(splashActivity, MainActivity::class.java)
            splashActivity.startActivity(intent)
        }


    }
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "", modifier = Modifier.size(250.dp))

        Spacer(modifier = Modifier.height(10.dp))

        CircularProgressIndicator()

    }

}