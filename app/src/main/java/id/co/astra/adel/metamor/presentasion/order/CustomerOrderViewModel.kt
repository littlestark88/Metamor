package id.co.astra.adel.metamor.presentasion.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.domain.customerorder.CustomerOrderUseCase
import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import javax.inject.Inject

@HiltViewModel
class CustomerOrderViewModel @Inject constructor(private val customerOrderUseCase: CustomerOrderUseCase): ViewModel() {

    val getAllCustomerWithOrder = customerOrderUseCase.getAllCustomerWithOrder().asLiveData()

    suspend fun insertCustomerOrder(customerOrder: List<CustomerOrder>) {
        customerOrderUseCase.insertCustomerOrder(customerOrder)
    }
}