package com.mopix.olineshopapp.models.invoices

import com.mopix.olineshopapp.models.user.User
import com.mopix.olineshopapp.models.user.UserVm

data class PaymentTransaction(
    var items:List<InvoiceItems>,
    var user: UserVm?
)
