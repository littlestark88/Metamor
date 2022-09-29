package id.co.astra.adel.metamor.data.additem

import id.co.astra.adel.metamor.data.additem.local.mapToEntity
import id.co.astra.adel.metamor.domain.additem.IAddItemRepository
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddItemRepository(
    private val addItemLocalSource: AddItemLocalSource
): IAddItemRepository {

    override suspend fun insertAddItem(addItem: AddItem) {
        addItemLocalSource.insertAddItem(addItem.mapToEntity())
    }

    override suspend fun updateAddItem(addItem: AddItem) {
        addItemLocalSource.updateAddItem(addItem.mapToEntity())
    }

    override suspend fun deleteAddItem(idItem: Int) {
        addItemLocalSource.deleteAddItem(idItem)
    }

    override fun getAllAddItem(): Flow<List<AddItem>> {
        return addItemLocalSource.getAllAddItem().map { DataMapper.mapToAddItemEntityToDomainList(it) }
    }

    override fun getDataByName(nameItem: String): Flow<List<AddItem>> {
        return addItemLocalSource.getDataByName(nameItem).map { DataMapper.mapToAddItemEntityToDomainList(it) }
    }

}