package id.co.astra.adel.metamor.domain.customerorder

import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder

interface ICustomerOrderRepository {
    suspend fun insertCustomerOrder(customerOrder: List<CustomerOrder>)
}