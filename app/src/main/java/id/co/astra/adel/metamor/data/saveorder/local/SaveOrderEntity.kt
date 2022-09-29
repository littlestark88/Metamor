package id.co.astra.adel.metamor.data.saveorder.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import id.co.astra.adel.metamor.data.order.local.OrderEntity

@Entity
data class SaveOrderEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCustomer")
    var idCustomer: Int = 0,

    @ColumnInfo(name = "nameCustomer")
    var nameCustomer: String?,

    @ColumnInfo(name = "numberPhone")
    var numberPhone: Int? = 0,

    @ColumnInfo(name = "email")
    var email: String?,

    @TypeConverters
    @ColumnInfo(name = "orderList")
    var orderList: List<SaveOrderItem>
)