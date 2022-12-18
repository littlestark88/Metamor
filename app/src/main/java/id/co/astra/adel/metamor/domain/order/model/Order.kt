package id.co.astra.adel.metamor.domain.order.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    var idItem: Int,
    var nameItem: String,
    var priceItem: Double,
    var quantityItem: Int,
    var discountItem: Int,
    var customerId: Int
): Parcelable
