package id.co.astra.adel.metamor.domain.order

import id.co.astra.adel.metamor.domain.order.model.Order
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    suspend fun insertOrder(order: Order)
    suspend fun updateOrder(order: Order)
    suspend fun deleteOrder(idItem: Int)
    fun getAllOrder(): Flow<List<Order>>
}