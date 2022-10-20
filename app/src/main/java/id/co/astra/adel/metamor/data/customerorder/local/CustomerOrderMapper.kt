package id.co.astra.adel.metamor.data.customerorder.local

import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder

fun CustomerOrder.mapToEntity() = List<CustomerOrderCrossRefEntity>(
    csId = csId,
    orId = orId
)