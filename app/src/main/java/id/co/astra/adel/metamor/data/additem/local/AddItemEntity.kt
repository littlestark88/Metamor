package id.co.astra.adel.metamor.data.additem.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.co.astra.adel.metamor.domain.additem.model.AddItem

@Entity
data class AddItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idItem")
    var idItem: Int = 0,

    @ColumnInfo(name = "nameItem")
    var nameItem: String? = null,

    @ColumnInfo(name = "priceItem")
    var priceItem: Double? = 0.0,

    @ColumnInfo(name = "image")
    var imageItem: Bitmap? = null

) {
    fun mapToDomainEntity(): AddItem {
        return AddItem (
            idItem = idItem,
            nameItem = nameItem.orEmpty(),
            priceItem = priceItem ?: 0.0,
            imageItem = imageItem
                )
    }
}