package id.co.astra.adel.metamor.data.order.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["nameItem"], unique = true)], )
data class OrderEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    var idItem: Int = 0,

    @ColumnInfo(name = "nameItem")
    var nameItem: String?,

    @ColumnInfo(name = "priceItem")
    var priceItem: Double?,

    @ColumnInfo(name = "quantityItem")
    var quantityItem: Int?,

    @ColumnInfo(name = "discountItem")
    var discountItem: Int?,

    @ColumnInfo(name = "customerId")
    var customerId: Int = 0
)
