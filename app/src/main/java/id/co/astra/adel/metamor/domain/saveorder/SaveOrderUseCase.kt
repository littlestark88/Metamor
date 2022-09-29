package id.co.astra.adel.metamor.domain.saveorder

import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import kotlinx.coroutines.flow.Flow

interface SaveOrderUseCase {
    suspend fun insertSaveOrder(saveOrder: SaveOrder)
    suspend fun updateSaveOrder(saveOrder: SaveOrder)
    suspend fun deleteSaveOrder(idCustomer: Int)
    fun getSaveOrder(): Flow<List<SaveOrder>>
    fun getSaveOrderById(idCustomer: Int): Flow<SaveOrder>
}