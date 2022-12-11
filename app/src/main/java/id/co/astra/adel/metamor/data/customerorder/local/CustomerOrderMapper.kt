package id.co.astra.adel.metamor.data.customerorder.local

import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder

object CustomerOrderMapper {
    fun mapToCustomerOrderEntity(data: List<CustomerOrder>): List<CustomerOrderCrossRefEntity> {
        val dataList = ArrayList<CustomerOrderCrossRefEntity>()
        data.map {
            val customerOrderCrossRefEntity = CustomerOrderCrossRefEntity(
                customerId = it.customerId,
                orderId = it.orderId
            )
            dataList.add(customerOrderCrossRefEntity)
        }
        return dataList
    }
}