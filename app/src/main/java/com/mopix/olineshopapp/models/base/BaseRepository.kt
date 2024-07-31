package com.mopix.olineshopapp.models.base

open class BaseRepository {
    protected fun prepareToken(token:String): String {
        var fixedToken = token
        if (!fixedToken.lowercase().startsWith("Bearer"))
            fixedToken = "Bearer $token"
        return fixedToken
    }
}