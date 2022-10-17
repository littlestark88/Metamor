package id.co.astra.adel.metamor.data.customer

import id.co.astra.adel.metamor.data.customer.local.CustomerDao
import id.co.astra.adel.metamor.data.customer.local.CustomerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerLocalSource @Inject constructor(private val customerDao: CustomerDao): ICustomerLocalSource {
    override suspend fun insertCustomer(customerEntity: CustomerEntity)  = customerDao.insertCustomer(customerEntity)

    override suspend fun updateCustomer(customerEntity: CustomerEntity) = customerDao.updateCustomer(customerEntity)

    override suspend fun deleteCustomer(idCustomer: Int) = customerDao.deleteCustomer(idCustomer)

    override fun getAllCustomer(): Flow<List<CustomerEntity>> = customerDao.getAllCustomer()
}