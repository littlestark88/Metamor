package id.co.astra.adel.metamor.data.customer

import id.co.astra.adel.metamor.data.customer.local.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface ICustomerLocalSource {
    suspend fun insertCustomer(customerEntity: CustomerEntity)
    suspend fun updateCustomer(customerEntity: CustomerEntity)
    suspend fun deleteCustomer(idCustomer: Int)
    fun getAllCustomer(): Flow<List<CustomerEntity>>
}