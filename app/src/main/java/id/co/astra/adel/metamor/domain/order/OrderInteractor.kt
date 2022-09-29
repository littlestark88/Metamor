package id.co.astra.adel.metamor.domain.order

import id.co.astra.adel.metamor.domain.order.model.Order
import kotlinx.coroutines.flow.Flow

class OrderInteractor(private val repository: IOrderRepository): OrderUseCase {
    override suspend fun insertOrder(order: Order) = repository.insertOrder(order)

    override suspend fun updateOrder(order: Order) = repository.updateOrder(order)

    override suspend fun deleteOrder(idItem: Int) = repository.deleteOrder(idItem)

    override fun getAllOrder(): Flow<List<Order>> = repository.getAllOrder()
}