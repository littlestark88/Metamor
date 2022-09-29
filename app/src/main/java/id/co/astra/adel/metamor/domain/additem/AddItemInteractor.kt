package id.co.astra.adel.metamor.domain.additem

import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.flow.Flow

class AddItemInteractor(private val addItemRepository: IAddItemRepository): AddItemUseCase {
    override suspend fun insertAddItem(addItem: AddItem) = addItemRepository.insertAddItem(addItem)
    override suspend fun updateAddItem(addItem: AddItem) = addItemRepository.updateAddItem(addItem)
    override suspend fun deleteAddItem(idItem: Int) = addItemRepository.deleteAddItem(idItem)
    override fun getAllAddItem(): Flow<List<AddItem>> = addItemRepository.getAllAddItem()
    override fun getDataByName(nameItem: String): Flow<List<AddItem>> = addItemRepository.getDataByName(nameItem)
}