package id.co.astra.adel.metamor.domain.customer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    var idCustomer: Int = 0,
    var nameCustomer: String,
    var numberPhone: Int,
    var email: String
): Parcelable
