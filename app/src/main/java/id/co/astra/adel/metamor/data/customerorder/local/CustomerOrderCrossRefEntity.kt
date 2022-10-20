package id.co.astra.adel.metamor.data.customerorder.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["csId","orId"])
data class CustomerOrderCrossRefEntity(
    val csId: Int,
    @ColumnInfo(index = true)
    val orId: Int,
)
