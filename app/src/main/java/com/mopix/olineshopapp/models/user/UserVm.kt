package com.mopix.olineshopapp.models.user

import com.mopix.olineshopapp.models.db.models.UserEntity

data class UserVm(
    var address: String? = "",
    var customerId: Long? = null,
    var firstname: String? = "",
    var id: Long? = null,
    var lasttname: String? = "",
    var oldpassword: String? = null,
    var password: String?= null,
    var phone: String? = "",
    var postalCode: String? = "",
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String? = ""



) {
    fun convertToUserEntity(): UserEntity {
        return UserEntity(
            userId = id!!,
            address = address,
            customerId = customerId!!,
            firstname = firstname,
            lastname = lasttname,
            phone = phone,
            postalCode = postalCode,
            token = token,
            username = username
            )
    }
}
