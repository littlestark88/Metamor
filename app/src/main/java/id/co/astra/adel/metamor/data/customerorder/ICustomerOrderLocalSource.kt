package id.co.astra.adel.metamor.data.customerorder

import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderCrossRefEntity
import id.co.astra.adel.metamor.data.customerorder.local.CustomerWithOrder
import kotlinx.coroutines.flow.Flow

interface ICustomerOrderLocalSource {
    suspend fun insertCustomerOrder(customerOrderCrossRefEntity: List<CustomerOrderCrossRefEntity>)
    fun getAllCustomerWithOrder(): Flow<List<CustomerWithOrder>>
}