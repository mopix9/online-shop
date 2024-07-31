package com.mopix.olineshopapp.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mopix.olineshopapp.models.db.viewmodels.UserEntityViewModel
import com.mopix.olineshopapp.models.user.UserVm
import com.mopix.olineshopapp.view.theme.Dark
import com.mopix.olineshopapp.viewmodels.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
){
    var isLoading by remember { mutableStateOf(false) }
    val currentuser by remember { mutableStateOf(userEntityViewModel.currentUser) }
    val isLoggedIn by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current


    var oldpassword by remember { mutableStateOf(TextFieldValue("")) }
    var newPassword by remember { mutableStateOf(TextFieldValue("")) }
    var NewPasswordError by remember { mutableStateOf(false) }

    var RepeatePassword by remember { mutableStateOf(TextFieldValue("")) }
    var RepeatePasswordError by remember { mutableStateOf(false) }

    var oldpasswordError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(15.dp)) {


        Row() {

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Please change your password",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp, modifier = Modifier.padding(0.dp, 13.dp, 0.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))




        OutlinedTextField(
            value = oldpassword,
            onValueChange = {
                oldpassword = it
                oldpasswordError = false
            },
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            }),
            label = { Text(text = "Old Password") },
            shape =
            RoundedCornerShape(20.dp),
            isError = oldpasswordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                if (oldpasswordError) {
                    Icon(
                        imageVector = Icons.Filled.Warning, tint = Color.Red,
                        contentDescription = ""
                    )
                } else
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = ""
                    )
            },
            placeholder = { Text(text = "Enter your password") },
            modifier = Modifier.fillMaxWidth()
        )
        if (oldpasswordError) {
            Text(
                text = "please enter Your password",
                color = Color.Red,
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                NewPasswordError = false
            },
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            }),
            label = { Text(text = " New Password") },
            shape =
            RoundedCornerShape(20.dp),
            isError = NewPasswordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {

                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = ""
                    )
            },
            placeholder = { Text(text = "Enter your New password") },
            modifier = Modifier.fillMaxWidth()
        )
        if (NewPasswordError) {
            Text(
                text = "please enter match password",
                color = Color.Red,
                fontSize = 15.sp
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = RepeatePassword,
            onValueChange = {
                RepeatePassword = it
                RepeatePasswordError = false
            },
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            }),
            label = { Text(text = " Repeate Password") },
            shape =
            RoundedCornerShape(20.dp),
            isError = RepeatePasswordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {

                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = ""
                    )
            },
            placeholder = { Text(text = "Repeat your password") },
            modifier = Modifier.fillMaxWidth()
        )
        if (RepeatePasswordError) {
            Text(
                text = "please enter match password",
                color = Color.Red,
                fontSize = 15.sp
            )
        }


        if (!isLoading) {

            Button(
                onClick = {
                    if (oldpassword.text.isEmpty())
                        oldpasswordError = true

                    if (oldpasswordError) return@Button
                    val userInfo = UserVm(
                        id =  currentuser.value!!.userId,
                        customerId =  currentuser.value!!.customerId,
                        oldpassword = oldpassword.text,
                        password = newPassword.text,
                        repeatPassword = RepeatePassword.text

                    )

                    if (newPassword.text!= RepeatePassword.text){
                        NewPasswordError = true
                        Toast.makeText(context, "password dont match", Toast.LENGTH_LONG).show()
                        return@Button
                    }
                    userViewModel.changepassword(userInfo) { response ->
                        if (response.status == "OK") {

                            CoroutineScope(Dispatchers.IO).launch {
                                val userEntity = userInfo.convertToUserEntity()
                                userEntity.id = currentuser.value!!.id
                                userEntity.token = currentuser.value!!.token!!
                                userEntityViewModel.update(userEntity)
                            }
                            userViewModel.login(
                                UserVm(
                                    username = currentuser.value!!.username,
                                    password = newPassword.text

                                )
                            ) { userResponse ->
                                if (userResponse.status == "OK") {
                                    val user = userResponse.data!![0]
                                    CoroutineScope(Dispatchers.IO).launch {
                                        userEntityViewModel.insert(user.convertToUserEntity())
                                    }

                                }
                            }
                            Toast.makeText(context, "password changed Succssfully!", Toast.LENGTH_LONG).show()
                            navController.navigate("home")
                        }
                    }



                }  , modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Dark) )
            {
                Text(text = "Change Password", fontSize = 20.sp, color = Color.White)
            }
        }
        if (isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }


    }

}
