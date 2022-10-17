package id.co.astra.adel.metamor.domain.checkout

import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutInteractor @Inject constructor(private val checkoutRepository: ICheckoutRepository): CheckoutUseCase {
    override suspend fun insertCheckout(checkout: Checkout) = checkoutRepository.insertCheckout(checkout)

    override suspend fun updateCheckout(checkout: Checkout) = checkoutRepository.updateCheckout(checkout)

    override fun deleteCheckout(checkout: Checkout) = checkoutRepository.deleteCheckout(checkout)

    override fun getAllCheckout(): Flow<List<Checkout>> = checkoutRepository.getAllCheckout()

    override fun getCheckout(id: Int): Flow<Checkout> = checkoutRepository.getCheckout(id)
}