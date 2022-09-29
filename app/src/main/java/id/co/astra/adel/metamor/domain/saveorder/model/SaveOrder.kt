package id.co.astra.adel.metamor.domain.saveorder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveOrder(
    var idCustomer: Int,
    var nameCustomer: String,
    var numberPhone: Int,
    var email: String,
    var orderList: List<SaveOrderList>
): Parcelable
