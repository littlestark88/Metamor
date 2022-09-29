package id.co.astra.adel.metamor.data.saveorder

import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderEntity
import kotlinx.coroutines.flow.Flow

interface ISaveOrderLocalSource {
    suspend fun insertSaveOrder(saveOrderEntity: SaveOrderEntity)
    suspend fun updateSaveOrder(saveOrderEntity: SaveOrderEntity)
    suspend fun deleteSaveOrder(idCustomer: Int)
    fun getSaveOrder(): Flow<List<SaveOrderEntity>>
    fun getSaveOrderById(idCustomer: Int): Flow<SaveOrderEntity>
}