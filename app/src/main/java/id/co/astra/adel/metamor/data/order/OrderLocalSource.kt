package id.co.astra.adel.metamor.data.order

import id.co.astra.adel.metamor.data.order.local.OrderDao
import id.co.astra.adel.metamor.data.order.local.OrderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderLocalSource @Inject constructor(private val orderDao: OrderDao): IOrderLocalSource {
    override suspend fun insertOrder(orderEntity: OrderEntity) = orderDao.insertTakeItem(orderEntity)

    override suspend fun updateOrder(orderEntity: OrderEntity) = orderDao.updateTakeItem(orderEntity)

    override suspend fun deleteOrder(idItem: Int) = orderDao.deleteTakeItem(idItem)

    override fun getAllOrder(): Flow<List<OrderEntity>> = orderDao.getAllTakeItem()

    override fun getOrderByCustomerId(customerId: Int): Flow<List<OrderEntity>> = orderDao.getOrderByCustomerId(customerId)
}