package com.mopix.olineshopapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mopix.olineshopapp.view.components.AdvancedButton
import com.mopix.olineshopapp.view.components.loading
import com.mopix.olineshopapp.viewmodels.invoices.InvoiceItemViewModel

@Composable
fun InvoiceScreen(
    navController: NavController,
    id: Long,
    viewModel: InvoiceItemViewModel = hiltViewModel()
) {
    var invoice by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
        loading(
            modifier = Modifier
                 .fillMaxSize()
        )
    } else {
        Column(Modifier.padding(10.dp)) {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "invoice", textAlign = TextAlign.Center,
                    fontSize = 20.sp, modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(50.dp),
                    backgroundColor = if (invoice.value!!.status == "unpayed") Red else
                        Color.Green
                ) {
                    Icon(
                        imageVector = if (invoice.value!!.status == "unpayed") Icons.Filled.Close
                        else Icons.Filled.Check,
                        contentDescription = "",
                        Modifier
                            .size(200.dp)
                            .padding(30.dp),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(text = "Status : ${invoice.value!!.status!!}", fontSize = 21.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Add Date : ${invoice.value!!.addDate!!}", fontSize = 21.sp)
                Spacer(modifier = Modifier.height(5.dp))
                if (!invoice.value!!.paymentDate.isNullOrEmpty()) {
                    Text(text = "Payment Date : ${invoice.value!!.paymentDate!!}", fontSize = 21.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(invoice.value!!.items!!.size) { index ->
                        AdvancedButton(
                            invoice.value!!.items!![index].product!!.title!!,
                            invoice.value!!.items!![index].quantity!!.toString(),
                            Icons.Filled.List,
                            Color.Green
                        ) {
                        }
                    }
                }
            }

        }
    }
}