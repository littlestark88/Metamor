package id.co.astra.adel.metamor.domain.customerorder

import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import javax.inject.Inject

class CustomerOrderInteractor @Inject constructor(private val repository: ICustomerOrderRepository): CustomerOrderUseCase {
    override suspend fun insertCustomerOrder(customerOrder: List<CustomerOrder>) {
        return repository.insertCustomerOrder(customerOrder)
    }

}