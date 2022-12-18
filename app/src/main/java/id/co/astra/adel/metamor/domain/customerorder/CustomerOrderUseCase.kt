package id.co.astra.adel.metamor.domain.customerorder

import id.co.astra.adel.metamor.data.customerorder.local.CustomerWithOrder
import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import kotlinx.coroutines.flow.Flow

interface CustomerOrderUseCase {
    suspend fun insertCustomerOrder(customerOrder: List<CustomerOrder>)
    fun getAllCustomerWithOrder(): Flow<List<CustomerWithOrder>>
}