package com.mopix.olineshopapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun EditProfileScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var isLoading by remember { mutableStateOf(false) }
    val currentuser by remember { mutableStateOf(userEntityViewModel.currentUser) }
    val isLoggedIn by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }

//     region mutableStetes
    var firstName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentuser.value!!.firstname!! else "")) }
    var firstNameError by remember { mutableStateOf(false) }

    var lastName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentuser.value!!.lastname!! else "")) }
    var lastNameError by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentuser.value!!.address!! else "")) }
    var addressError by remember { mutableStateOf(false) }

    var phone by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentuser.value!!.phone!! else "")) }
    var phoneError by remember { mutableStateOf(false) }

    var postalCode by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentuser.value!!.postalCode!! else "")) }
    var postalCodeError by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentuser.value!!.username!! else "")) }
    var usernameError by remember { mutableStateOf(false) }


// endregion


    Column() {


        Row() {

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Enter Your Personal Information",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp, modifier = Modifier.padding(0.dp, 13.dp, 0.dp, 0.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            item {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                        firstNameError = false
                    },
                    label = { Text(text = "First Name") },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = firstNameError,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    singleLine = true,
                    shape =
                    RoundedCornerShape(20.dp),

                    placeholder = { Text(text = "Enter your First Name") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        if (firstNameError) {
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
                )
                if (firstNameError) {
                    Text(text = "please enter Your firstName", color = Color.Red, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                        lastNameError = false
                    },
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    singleLine = true,
                    label = { Text(text = "Last Name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),

                    isError = lastNameError,
                    shape =
                    RoundedCornerShape(20.dp),
                    leadingIcon = {
                        if (firstNameError) {
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
                    placeholder = { Text(text = "Enter your Last Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (lastNameError) {
                    Text(text = "please enter Your lastName", color = Color.Red, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = username,
                    enabled = (currentuser.value!!.username == null),
                    onValueChange = {
                        username = it
                        usernameError = false
                    },
//                    enabled = if (currentuser.username == null)true,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    singleLine = true,
                    label = { Text(text = "User Name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),


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
                    Text(text = "please enter Your userName", color = Color.Red, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = address,
                    onValueChange = {
                        address = it
                        addressError = false
                    },
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    shape =
                    RoundedCornerShape(20.dp),
                    label = { Text(text = "Address") },
                    isError = addressError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                    leadingIcon = {
                        if (addressError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, tint = Color.Red,
                                contentDescription = ""
                            )
                        } else
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = ""
                            )
                    },
                    placeholder = { Text(text = "Enter your Address") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (addressError) {
                    Text(text = "please enter Your address", color = Color.Red, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = phone,
                    singleLine = true,
                    onValueChange = {
                        phone = it
                        phoneError = false
                    },
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    shape =
                    RoundedCornerShape(20.dp),

                    label = { Text(text = "Phone Number") },
                    isError = phoneError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        if (phoneError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, tint = Color.Red,
                                contentDescription = ""
                            )
                        } else
                            Icon(
                                imageVector = Icons.Filled.Phone,
                                contentDescription = ""
                            )
                    },
                    placeholder = { Text(text = "Enter your Phone number") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (phoneError) {
                    Text(
                        text = "please enter Your phone number",
                        color = Color.Red,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = {
                        postalCode = it
                        postalCodeError = false
                    },
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }),
                    singleLine = true,
                    label = { Text(text = "Postal Code") },
                    isError = postalCodeError,
                    shape =
                    RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        if (postalCodeError) {
                            Icon(
                                imageVector = Icons.Filled.Warning, tint = Color.Red,
                                contentDescription = ""
                            )
                        } else
                            Icon(
                                imageVector = Icons.Filled.Place,
                                contentDescription = ""
                            )
                    },
                    placeholder = { Text(text = "Enter your Postal Code") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (postalCodeError) {
                    Text(text = "please enter Your postalCode", color = Color.Red, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {


                if (!isLoading) {

                    Button(
                        onClick = {
                            if (firstName.text.isEmpty())
                                firstNameError = true

                            if (lastName.text.isEmpty())
                                lastNameError = true

                            if (username.text.isEmpty())
                                usernameError = true

                            if (address.text.isEmpty())
                                addressError = true

                            if (phone.text.isEmpty())
                                phoneError = true

                            if (postalCode.text.isEmpty())
                                postalCodeError = true

                            if (lastNameError || firstNameError || addressError || usernameError || postalCodeError || phoneError) {
                                return@Button

                            }


                            val userInfo = UserVm(
                                id = currentuser.value!!.userId,
                                customerId = currentuser.value!!.customerId,
                                firstname = firstName.text,
                                lasttname = lastName.text,
                                username = username.text,
                                address = address.text,
                                phone = phone.text,
                                postalCode = postalCode.text
                            )
                            userViewModel.update(userInfo) { response ->
                                if (response.status == "OK") {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        val userEntity = userInfo.convertToUserEntity()
                                        userEntity.id = currentuser.value!!.id
                                        userEntity.token = currentuser.value!!.token!!
                                        userEntityViewModel.update(userEntity)
                                    }
                                    navController.popBackStack()
                                }


                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Dark)
                    ) {
                        Text(text = "update Your Profile", fontSize = 20.sp, color = Color.White)
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
    }


}