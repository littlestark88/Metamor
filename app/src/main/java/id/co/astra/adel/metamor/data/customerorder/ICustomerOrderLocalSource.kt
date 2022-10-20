package id.co.astra.adel.metamor.data.customerorder

import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderCrossRefEntity

interface ICustomerOrderLocalSource {
    suspend fun insertCustomerOrder(customerOrderCrossRefEntity: List<CustomerOrderCrossRefEntity>)
}