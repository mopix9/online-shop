package com.mopix.olineshopapp.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.navigation.NavController
import com.mopix.olineshopapp.models.invoices.Invoice

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavController) {

    AdvancedButton(
        invoice.addDate!!, invoice.status!!,
        if (invoice.status == "unpayed") Icons.Filled.Close else
            Icons.Filled.Check,
        if (invoice.status == "unpayed") Red else
            Color.Green
    ) {
        navController.navigate("invoice/${invoice.id}")
    }
}