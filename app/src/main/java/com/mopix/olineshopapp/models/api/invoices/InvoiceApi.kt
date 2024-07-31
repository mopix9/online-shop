package com.mopix.olineshopapp.models.api.invoices

import com.mopix.olineshopapp.models.ServiceResponse
import com.mopix.olineshopapp.models.invoices.Invoice
import retrofit2.http.*

interface InvoiceApi {

    @POST("/api/invoice}")
    suspend fun addInvoice(
        @Body data: Invoice,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userId}")
    suspend fun getInvoiceByUserId(
        @Path("userId") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>
}