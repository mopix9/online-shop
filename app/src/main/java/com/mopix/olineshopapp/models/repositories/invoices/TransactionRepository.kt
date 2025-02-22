package com.mopix.olineshopapp.models.repositories.invoices

import com.mopix.olineshopapp.models.api.invoices.TransactionApi
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.invoices.PaymentTransaction
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val api: TransactionApi) {
     suspend fun gotoPayment(data:PaymentTransaction): ServiceResponse<String> {
        return try {
            api.gotopayment(data)
        }catch (e:Exception){
            ServiceResponse(status = "EXECPTION", message = e.message)
        }
    }

}