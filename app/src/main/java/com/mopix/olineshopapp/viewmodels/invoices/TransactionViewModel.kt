package com.mopix.olineshopapp.viewmodels.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.invoices.PaymentTransaction
import com.mopix.olineshopapp.models.repositories.invoices.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val repository: TransactionRepository):ViewModel() {


    fun gotoPayment(Data: PaymentTransaction, onSuccess : (response: ServiceResponse<String>)-> Unit){
        viewModelScope.launch {
            val data = repository.gotoPayment(Data)
            onSuccess(data)
        }
    }
}