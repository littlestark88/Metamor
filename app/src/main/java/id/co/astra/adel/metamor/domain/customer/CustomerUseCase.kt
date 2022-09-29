package id.co.astra.adel.metamor.domain.customer

import id.co.astra.adel.metamor.domain.customer.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerUseCase {
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun deleteCustomer(idCustomer: Int)
    fun getAllCustomer(): Flow<List<Customer>>
}