package com.mopix.olineshopapp.models

class ServiceResponse<T> (
    var data :List<T>? = null,
    var status:String?= null,
    var message:String?= null,
    var totalCount:Long? = null
        )