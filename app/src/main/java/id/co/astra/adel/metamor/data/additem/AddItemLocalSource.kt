package id.co.astra.adel.metamor.data.additem

import id.co.astra.adel.metamor.data.additem.local.AddItemDao
import id.co.astra.adel.metamor.data.additem.local.AddItemEntity
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.flow.Flow

class AddItemLocalSource(private val addItemDao: AddItemDao) : IAddItemLocalSource {
    override suspend fun insertAddItem(addItemEntity: AddItemEntity) = addItemDao.insertAddItem(addItemEntity)
    override suspend fun updateAddItem(addItemEntity: AddItemEntity) = addItemDao.updateAddItem(addItemEntity)
    override suspend fun deleteAddItem(idItem: Int) = addItemDao.deleteAddItem(idItem)
    override fun getAllAddItem(): Flow<List<AddItemEntity>> = addItemDao.getAllAddItem()
    override fun getDataByName(nameItem: String): Flow<List<AddItemEntity>>  = addItemDao.getDataByName(nameItem)
}