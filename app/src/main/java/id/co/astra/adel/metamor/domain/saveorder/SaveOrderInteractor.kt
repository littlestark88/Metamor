package id.co.astra.adel.metamor.domain.saveorder

import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import kotlinx.coroutines.flow.Flow

class SaveOrderInteractor(private val saveOrderRepository: ISaveOrderRepository): SaveOrderUseCase {
    override suspend fun insertSaveOrder(saveOrder: SaveOrder) {
        saveOrderRepository.insertSaveOrder(saveOrder)
    }

    override suspend fun updateSaveOrder(saveOrder: SaveOrder) {
        saveOrderRepository.updateSaveOrder(saveOrder)
    }

    override suspend fun deleteSaveOrder(idCustomer: Int) {
        saveOrderRepository.deleteSaveOrder(idCustomer)
    }

    override fun getSaveOrder(): Flow<List<SaveOrder>> = saveOrderRepository.getSaveOrder()

    override fun getSaveOrderById(idCustomer: Int): Flow<SaveOrder> = saveOrderRepository.getSaveOrderById(idCustomer)
}