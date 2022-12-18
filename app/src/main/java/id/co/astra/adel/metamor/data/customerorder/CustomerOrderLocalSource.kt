package id.co.astra.adel.metamor.data.customerorder

import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderCrossRefEntity
import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderDao
import id.co.astra.adel.metamor.data.customerorder.local.CustomerWithOrder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerOrderLocalSource @Inject constructor(private val customerOrderDao: CustomerOrderDao): ICustomerOrderLocalSource{
    override suspend fun insertCustomerOrder(customerOrderCrossRefEntity: List<CustomerOrderCrossRefEntity>) {
        return customerOrderDao.insertCustomerOrder(customerOrderCrossRefEntity)
    }

    override fun getAllCustomerWithOrder(): Flow<List<CustomerWithOrder>> {
        return customerOrderDao.getAllCustomerWithOrder()
    }
}