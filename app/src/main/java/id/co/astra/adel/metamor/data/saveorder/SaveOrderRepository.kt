package id.co.astra.adel.metamor.data.saveorder

import id.co.astra.adel.metamor.data.saveorder.local.mapToEntity
import id.co.astra.adel.metamor.domain.saveorder.ISaveOrderRepository
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import id.co.astra.adel.metamor.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SaveOrderRepository(private val saveOrderLocalSource: SaveOrderLocalSource): ISaveOrderRepository {
    override suspend fun insertSaveOrder(saveOrder: SaveOrder) {
        saveOrderLocalSource.insertSaveOrder(saveOrder.mapToEntity())
    }

    override suspend fun updateSaveOrder(saveOrder: SaveOrder) {
        saveOrderLocalSource.updateSaveOrder(saveOrder.mapToEntity())
    }

    override suspend fun deleteSaveOrder(idCustomer: Int) {
        saveOrderLocalSource.deleteSaveOrder(idCustomer)
    }

    override fun getSaveOrder(): Flow<List<SaveOrder>> {
        return saveOrderLocalSource.getSaveOrder().map { DataMapper.mapSaveOrderEntityToDomain(it) }
    }

    override fun getSaveOrderById(idCustomer: Int): Flow<SaveOrder> {
        return saveOrderLocalSource.getSaveOrderById(idCustomer).map { DataMapper.mapGetSaveOrderEntityToDomain(it) }
    }
}