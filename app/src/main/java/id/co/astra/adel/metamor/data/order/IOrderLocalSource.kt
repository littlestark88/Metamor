package id.co.astra.adel.metamor.data.order

import id.co.astra.adel.metamor.data.order.local.OrderEntity
import kotlinx.coroutines.flow.Flow

interface IOrderLocalSource {
    suspend fun insertOrder(orderEntity: OrderEntity)
    suspend fun updateOrder(orderEntity: OrderEntity)
    suspend fun deleteOrder(idItem: Int)
    fun getAllOrder(): Flow<List<OrderEntity>>
    fun getOrderByCustomerId(customerId: Int): Flow<List<OrderEntity>>
}