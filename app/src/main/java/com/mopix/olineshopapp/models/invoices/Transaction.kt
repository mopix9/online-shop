package com.mopix.olineshopapp.models.invoices

data class Transaction(
    var id: Long?,
    var ShaparakRefId: String?,
    var amount: Long?,
    var cardHolder: String?,
    var code: Int?,
    var custom: String?,
    var customerPhone: String?,
    var orderId: String?,
    var refId: String?,
    var refundRequest: String?,
    var transId: String?
)
