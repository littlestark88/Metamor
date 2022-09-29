package id.co.astra.adel.metamor.presentasion.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.astra.adel.metamor.domain.customer.CustomerUseCase
import id.co.astra.adel.metamor.domain.customer.model.Customer

class CustomerViewModel(private val customerUseCase: CustomerUseCase): ViewModel() {

    val getAllCustomer = customerUseCase.getAllCustomer().asLiveData()

    suspend fun insertCustomer(customer: Customer) {
        customerUseCase.insertCustomer(customer)
    }

    suspend fun updateCustomer(customer: Customer) {
        customerUseCase.updateCustomer(customer)
    }

    suspend fun deleteCustomer(idCustomer: Int) {
        customerUseCase.deleteCustomer(idCustomer)
    }
}