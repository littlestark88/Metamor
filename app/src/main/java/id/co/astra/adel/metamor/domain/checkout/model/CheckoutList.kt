package id.co.astra.adel.metamor.domain.checkout.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckoutList(
    var idItem: Int,
    var nameItem: String,
    var priceItem: Double,
    var quantityItem: Int,
    var discountItem: Int
): Parcelable
