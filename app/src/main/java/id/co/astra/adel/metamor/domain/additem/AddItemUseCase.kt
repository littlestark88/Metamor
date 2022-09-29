package id.co.astra.adel.metamor.domain.additem

import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.flow.Flow

interface AddItemUseCase {
    suspend fun insertAddItem(addItem: AddItem)
    suspend fun updateAddItem(addItem: AddItem)
    suspend fun deleteAddItem(idItem: Int)
    fun getAllAddItem(): Flow<List<AddItem>>
    fun getDataByName(nameItem: String): Flow<List<AddItem>>
}