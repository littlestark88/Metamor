package id.co.astra.adel.metamor.data.customerorder.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["customerId","orderId"])
data class CustomerOrderCrossRefEntity(
    val customerId: Int,
    @ColumnInfo(index = true)
    val orderId: Int,
)
