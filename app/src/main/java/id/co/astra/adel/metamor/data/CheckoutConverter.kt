package id.co.astra.adel.metamor.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import id.co.astra.adel.metamor.data.checkout.local.CheckoutItem

class CheckoutConverter {
    @TypeConverter
    fun listCheckout(data: List<CheckoutItem>?): String? = Gson().toJson(data)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<CheckoutItem>::class.java).toList()
}