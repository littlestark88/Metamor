package id.co.astra.adel.metamor.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderItem

class OrderConverter {

    @TypeConverter
    fun listSaveOrder(data: List<SaveOrderItem>?): String? = Gson().toJson(data)

    @TypeConverter
    fun jsonSaveOrderToList(value: String) = Gson().fromJson(value, Array<SaveOrderItem>::class.java).toList()
}