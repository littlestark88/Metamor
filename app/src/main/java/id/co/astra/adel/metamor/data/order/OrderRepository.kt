package id.co.astra.adel.metamor.data.order

import id.co.astra.adel.metamor.data.order.local.mapToEntity
import id.co.astra.adel.metamor.domain.order.IOrderRepository
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(private val orderLocalSource: OrderLocalSource): IOrderRepository {
    override suspend fun insertOrder(order: Order) {
        orderLocalSource.insertOrder(order.mapToEntity())
    }

    override suspend fun updateOrder(order: Order) {
        orderLocalSource.updateOrder(order.mapToEntity())
    }

    override suspend fun deleteOrder(idItem: Int) {
        orderLocalSource.deleteOrder(idItem)
    }

    override fun getAllOrder(): Flow<List<Order>> {
        return orderLocalSource.getAllOrder().map { DataMapper.mapToOrderEntityToDomainList(it) }
    }

    override fun getOrderByCustomerId(customerId: Int): Flow<List<Order>> {
        return orderLocalSource.getOrderByCustomerId(customerId).map { DataMapper.mapToOrderEntityToDomainList(it) }
    }
}