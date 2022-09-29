package id.co.astra.adel.metamor.domain.customer

import id.co.astra.adel.metamor.domain.customer.model.Customer
import kotlinx.coroutines.flow.Flow

class CustomerInteractor(private val repository: ICustomerRepository):CustomerUseCase {
    override suspend fun insertCustomer(customer: Customer) = repository.insertCustomer(customer)

    override suspend fun updateCustomer(customer: Customer) = repository.updateCustomer(customer)

    override suspend fun deleteCustomer(idCustomer: Int) = repository.deleteCustomer(idCustomer)

    override fun getAllCustomer(): Flow<List<Customer>> = repository.getAllCustomer()
}