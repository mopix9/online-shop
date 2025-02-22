package com.mopix.olineshopapp.models.invoices

import com.mopix.olineshopapp.models.user.User

data class Invoice(
    var id: Long?,
    var addDate: String?,
    var items: List<InvoiceItems>?,
    var paymentDate: String?,
    var status: String?,
    var tranactions:List<Transaction>?,
    var user: User?,
)
