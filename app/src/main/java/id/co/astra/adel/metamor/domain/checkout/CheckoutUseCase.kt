package id.co.astra.adel.metamor.domain.checkout

import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import kotlinx.coroutines.flow.Flow

interface CheckoutUseCase {
    suspend fun insertCheckout(checkout: Checkout)
    suspend fun updateCheckout(checkout: Checkout)
    fun deleteCheckout(checkout: Checkout)
    fun getAllCheckout(): Flow<List<Checkout>>
    fun getCheckout(id: Int): Flow<Checkout>
}