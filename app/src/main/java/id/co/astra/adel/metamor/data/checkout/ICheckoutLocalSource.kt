package id.co.astra.adel.metamor.data.checkout

import id.co.astra.adel.metamor.data.checkout.local.CheckoutEntity
import kotlinx.coroutines.flow.Flow

interface ICheckoutLocalSource {
    suspend fun insertCheckout(checkoutEntity: CheckoutEntity)
    suspend fun updateCheckout(checkoutEntity: CheckoutEntity)
    fun deleteCheckout(checkoutEntity: CheckoutEntity)
    fun getAllCheckout(): Flow<List<CheckoutEntity>>
    fun getCheckout(id: Int): Flow<CheckoutEntity>
}