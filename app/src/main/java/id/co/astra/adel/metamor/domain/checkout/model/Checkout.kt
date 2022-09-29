package id.co.astra.adel.metamor.domain.checkout.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checkout(
    var idCustomer: Int,
    var nameCustomer: String,
    var numberPhone: Int,
    var email: String,
    var total: Double,
    var ppn: Double,
    var quantity: Int,
    var typePayment: String,
    var cash: Double,
    var cashReturn: Double,
    var date: String,
    var checkoutItem: List<CheckoutList>
): Parcelable
