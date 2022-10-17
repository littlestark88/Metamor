package id.co.astra.adel.metamor.domain.additem.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddItem(
    var idItem: Int = 0,
    var nameItem: String,
    var priceItem: Double,
    var imageItem: Bitmap? = null
):Parcelable
