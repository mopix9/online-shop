package com.mopix.olineshopapp.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun LoginScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var username by remember { mutableStateOf(TextFieldValue()) }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    LazyColumn {
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
        }

        item {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text(text = "Let's sign you in.", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Welcome back.", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "You've been missed!", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = username,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    onValueChange = {
                        username = it
                        usernameError = false
                    },
                    singleLine = true,
                    label = { Text(text = "User Name") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),

                    shape =
                    RoundedCornerShape(20.dp),
                    isError = usernameError,

                    leadingIcon = {
                        if (usernameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, tint = Color.Red,
                                contentDescription = ""
                            )
                        } else
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = ""
                            )
                    },
                    placeholder = { Text(text = "Enter your User Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (usernameError) {
                    Text(text = "please enter Your userName", color = Color.Red,
                        fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = password,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text(text = "Password") },
                    shape =
                    RoundedCornerShape(20.dp),
                    isError = passwordError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        if (passwordError) {
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
                if (passwordError) {
                    Text(text = "please enter Your password", color = Color.Red,
                        fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
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
                }else {
                    Button(
                        onClick = {

                            if (username.text.isEmpty()) {
                                usernameError = true
                            }
                            if (password.text.isEmpty()) {
                                passwordError = true
                            }
                            if (usernameError || passwordError) return@Button


                            isLoading = true

                            userViewModel.login(
                                UserVm(
                                    username = username.text,
                                    password = password.text
                                )
                            ) { response ->
                                if (response.status == "OK") {
                                    val user = response.data!![0]
                                    CoroutineScope(Dispatchers.IO).launch {
                                        userEntityViewModel.insert(user.convertToUserEntity())
                                    }
                                    Toast.makeText(
                                        context,
                                        "Welcome back dear ${user.firstname}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("home")
                                }
                                isLoading = false
                            }
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Dark
                        )
                    ) {
                        Text(
                            text = "Login", fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}