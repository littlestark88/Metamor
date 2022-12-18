package id.co.astra.adel.metamor.data.customerorder

import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderMapper
import id.co.astra.adel.metamor.data.customerorder.local.CustomerWithOrder
import id.co.astra.adel.metamor.domain.customerorder.ICustomerOrderRepository
import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerOrderRepository @Inject constructor(private val customerOrderLocalSource: CustomerOrderLocalSource): ICustomerOrderRepository {

    override suspend fun insertCustomerOrder(customerOrder: List<CustomerOrder>) {
        return customerOrderLocalSource.insertCustomerOrder(CustomerOrderMapper.mapToCustomerOrderEntity(customerOrder))
    }

    override fun getAllCustomerWithOrder(): Flow<List<CustomerWithOrder>> {
        return customerOrderLocalSource.getAllCustomerWithOrder()
    }
}