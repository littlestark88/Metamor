package id.co.astra.adel.metamor.presentasion.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.domain.checkout.CheckoutUseCase
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val checkoutUseCase: CheckoutUseCase): ViewModel() {

    val getAllCheckout = checkoutUseCase.getAllCheckout().asLiveData()

    suspend fun insertCheckout(checkout: Checkout) {
        checkoutUseCase.insertCheckout(checkout)
    }

    fun getCheckoutById(id: Int) = checkoutUseCase.getCheckout(id).asLiveData()


    suspend fun updateCheckout(checkout: Checkout) {
        checkoutUseCase.updateCheckout(checkout)
    }

    fun deleteCheckout(checkout: Checkout) {
        checkoutUseCase.deleteCheckout(checkout)
    }
}