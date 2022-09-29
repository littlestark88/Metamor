package id.co.astra.adel.metamor.presentasion.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.astra.adel.metamor.domain.customer.model.Customer
import id.co.astra.adel.metamor.domain.order.OrderUseCase
import id.co.astra.adel.metamor.domain.order.model.Order

class OrderViewModel(private val orderUseCase: OrderUseCase): ViewModel() {

    val getAllOrder = orderUseCase.getAllOrder().asLiveData()

    suspend fun insertOrder(order: Order) {
        orderUseCase.insertOrder(order)
    }

    suspend fun updateCustomer(order: Order) {
        orderUseCase.updateOrder(order)
    }

    suspend fun deleteCustomer(idItem: Int) {
        orderUseCase.deleteOrder(idItem)
    }
}