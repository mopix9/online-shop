package com.mopix.olineshopapp.models.repositories.invoices

import com.mopix.olineshopapp.models.api.invoices.InvoiceApi
import com.mopix.olineshopapp.models.base.BaseRepository
import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.invoices.Invoice
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepository() {

    suspend fun getInvoiceById(
        id: Long,
        token: String
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceById(id, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceByUserId(userId, pageIndex, pageSize, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "salam-EXCEPTION", message = e.message)

        }
    }

    suspend fun addInvoice(
        data: Invoice,
        token: String
    ): ServiceResponse<Invoice> {
        return try {
            api.addInvoice(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}