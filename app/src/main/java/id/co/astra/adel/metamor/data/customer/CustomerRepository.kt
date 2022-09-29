package id.co.astra.adel.metamor.data.customer

import id.co.astra.adel.metamor.data.customer.local.mapToEntity
import id.co.astra.adel.metamor.domain.customer.ICustomerRepository
import id.co.astra.adel.metamor.domain.customer.model.Customer
import id.co.astra.adel.metamor.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CustomerRepository(private val customerLocalSource: CustomerLocalSource): ICustomerRepository {
    override suspend fun insertCustomer(customer: Customer) {
        customerLocalSource.insertCustomer(customer.mapToEntity())
    }

    override suspend fun updateCustomer(customer: Customer) {
        customerLocalSource.updateCustomer(customer.mapToEntity())
    }

    override suspend fun deleteCustomer(idCustomer: Int) {
        customerLocalSource.deleteCustomer(idCustomer)
    }

    override fun getAllCustomer(): Flow<List<Customer>> {
        return customerLocalSource.getAllCustomer().map { DataMapper.mapToCustomerEntityToDomainList(it) }
    }
}