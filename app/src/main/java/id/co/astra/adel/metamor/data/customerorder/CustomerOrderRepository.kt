package id.co.astra.adel.metamor.data.customerorder

import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderMapper
import id.co.astra.adel.metamor.domain.customerorder.ICustomerOrderRepository
import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerOrderRepository @Inject constructor(private val customerOrderLocalSource: CustomerOrderLocalSource): ICustomerOrderRepository {

    override suspend fun insertCustomerOrder(customerOrder: List<CustomerOrder>) {
        return customerOrderLocalSource.insertCustomerOrder(CustomerOrderMapper.mapToCustomerOrderEntity(customerOrder))
    }
}