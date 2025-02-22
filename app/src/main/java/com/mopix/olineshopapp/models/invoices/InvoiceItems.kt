package com.mopix.olineshopapp.models.invoices

import com.mopix.olineshopapp.models.db.models.BasketEntity
import com.mopix.olineshopapp.models.products.Product

data class  InvoiceItems(
    var id: Long? = null,
    var product: Product? ,
    var quantity: Int? ,
    var unitPrice: Long? = 0


){
    companion object{
        fun convertForBasket(basketEntity: BasketEntity): InvoiceItems {
            return InvoiceItems(
                product = Product(id = basketEntity.productId),
                quantity = basketEntity.quantity

            )

        }
    }
}
