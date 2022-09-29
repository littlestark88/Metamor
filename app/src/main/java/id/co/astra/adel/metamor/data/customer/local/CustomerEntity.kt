package id.co.astra.adel.metamor.data.customer.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCustomer")
    var idCustomer: Int = 0,

    @ColumnInfo(name = "nameCustomer")
    var nameCustomer: String?,

    @ColumnInfo(name = "numberPhone")
    var numberPhone: Int? = 0,

    @ColumnInfo(name = "email")
    var email: String?
)
