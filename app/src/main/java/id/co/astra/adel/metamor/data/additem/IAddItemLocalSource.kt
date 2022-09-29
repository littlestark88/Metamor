package id.co.astra.adel.metamor.data.additem

import id.co.astra.adel.metamor.data.additem.local.AddItemEntity
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.flow.Flow

interface IAddItemLocalSource {
    suspend fun insertAddItem(addItemEntity: AddItemEntity)
    suspend fun updateAddItem(addItemEntity: AddItemEntity)
    suspend fun deleteAddItem(idItem: Int)
    fun getAllAddItem(): Flow<List<AddItemEntity>>
    fun getDataByName(nameItem: String): Flow<List<AddItemEntity>>
}