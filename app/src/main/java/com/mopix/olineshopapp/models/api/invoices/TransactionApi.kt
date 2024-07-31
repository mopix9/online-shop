package com.mopix.olineshopapp.models.api.invoices

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.invoices.PaymentTransaction
import retrofit2.http.*

interface TransactionApi {

    @POST("/api/trx/gotopayment")
    suspend fun gotopayment(
        @Body data: PaymentTransaction
    ): ServiceResponse<String>


}