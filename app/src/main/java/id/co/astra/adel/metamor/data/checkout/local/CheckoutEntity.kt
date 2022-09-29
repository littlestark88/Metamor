package id.co.astra.adel.metamor.data.checkout.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class CheckoutEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCustomer")
    var idCustomer: Int = 0,

    @ColumnInfo(name = "nameCustomer")
    var nameCustomer: String?,

    @ColumnInfo(name = "numberPhone")
    var numberPhone: Int?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "total")
    var total: Double?,

    @ColumnInfo(name = "ppn")
    var ppn: Double?,

    @ColumnInfo(name = "quantity")
    var quantity: Int?,

    @ColumnInfo(name = "typePayment")
    var typePayment: String?,

    @ColumnInfo(name = "cash")
    var cash: Double?,

    @ColumnInfo(name = "cashReturn")
    var cashReturn: Double?,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @TypeConverters
    @ColumnInfo(name = "order")
    var checkoutItem: List<CheckoutItem>?
)