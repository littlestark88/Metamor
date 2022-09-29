package id.co.astra.adel.metamor.domain.saveorder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveOrderList(
    var idItem: Int,
    var nameItem: String,
    var priceItem: Double,
    var quantityItem: Int,
    var discountItem: Int
): Parcelable
