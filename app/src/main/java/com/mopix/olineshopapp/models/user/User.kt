package com.mopix.olineshopapp.models.user

data class User(
    var id: Long?,
    var customer: Customer?,
    var password: String?,
    var username: String?
)
