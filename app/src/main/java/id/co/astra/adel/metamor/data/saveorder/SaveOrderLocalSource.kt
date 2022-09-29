package id.co.astra.adel.metamor.data.saveorder

import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderDao
import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderEntity
import kotlinx.coroutines.flow.Flow

class SaveOrderLocalSource(private val saveOrderDao: SaveOrderDao): ISaveOrderLocalSource {
    override suspend fun insertSaveOrder(saveOrderEntity: SaveOrderEntity) {
        return saveOrderDao.insertSaveOrder(saveOrderEntity)
    }

    override suspend fun updateSaveOrder(saveOrderEntity: SaveOrderEntity) {
        return saveOrderDao.updateSaveOrder(saveOrderEntity)
    }

    override suspend fun deleteSaveOrder(idCustomer: Int) {
        return saveOrderDao.deleteSaveOrder(idCustomer)
    }

    override fun getSaveOrder(): Flow<List<SaveOrderEntity>> {
        return saveOrderDao.getSaveOrder()
    }

    override fun getSaveOrderById(idCustomer: Int): Flow<SaveOrderEntity> {
        return saveOrderDao.getSaveOrderById(idCustomer)
    }
}