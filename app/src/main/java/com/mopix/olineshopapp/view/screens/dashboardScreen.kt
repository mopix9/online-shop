package com.mopix.olineshopapp.view.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.models.db.viewmodels.UserEntityViewModel
import com.mopix.olineshopapp.view.components.AdvancedButton
import com.mopix.olineshopapp.view.theme.IranianGreen
import com.mopix.olineshopapp.view.theme.Red
import com.mopix.olineshopapp.view.theme.lightBlue
import com.mopix.olineshopapp.view.utiles.ThisApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {


    val currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = "User Profile", textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        Row(Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier.padding(0.dp),
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "",
                    Modifier.size(70.dp),
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
                    text = "${currentUser.value!!.firstname} ${currentUser.value!!.lastname}",
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${currentUser.value!!.username}",
                    fontSize = 18.sp,
                    color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                )
            }
            IconButton(onClick = {
                ThisApp.token = currentUser.value!!.token!!
                navController.navigate("editProfile")
            }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Account", Modifier.padding(20.dp), fontSize = 22.sp)

        LazyColumn {
            item {
                AdvancedButton("Invoices", "Show Your Invoices", Icons.Outlined.Star, Color.Blue) {
                    ThisApp.userId = currentUser.value!!.userId
                    ThisApp.token = currentUser.value!!.token!!
                    navController.navigate("invoices")
                }
                AdvancedButton(
                    "Change Password",
                    "Change Your Password",
                    Icons.Outlined.Lock,
                    IranianGreen
                ) {
                    ThisApp.token = currentUser.value!!.token!!
                    navController.navigate("changePassword")
                }
                AdvancedButton("Help", "Help And Feedback", Icons.Outlined.Info, lightBlue) {
                }
                AdvancedButton("Logout", "Logout", Icons.Outlined.ExitToApp, Red) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()
                    }
                    navController.navigate("home")
                }
            }
        }
    }
}