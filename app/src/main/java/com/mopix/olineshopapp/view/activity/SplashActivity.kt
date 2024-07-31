package com.mopix.olineshopapp.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.mopix.olineshopapp.models.db.viewmodels.UserEntityViewModel
import com.mopix.olineshopapp.view.screens.SplashScreen
import com.mopix.olineshopapp.view.theme.OlineshopappTheme
import com.mopix.olineshopapp.view.utiles.ThisApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var Load by remember{ mutableStateOf(false)}
            val userEntityViewModel =
                ViewModelProvider(this).get(UserEntityViewModel::class.java)

            userEntityViewModel.getCurrentUser().observe(this){observed ->
                Load =true
                userEntityViewModel.currentUser.value = observed

            }
            OlineshopappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (userEntityViewModel.currentUser.value != null) {
                        ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    }
                    if (Load)
                        SplashScreen(this, userEntityViewModel)
                }
            }
        }
    }
}
