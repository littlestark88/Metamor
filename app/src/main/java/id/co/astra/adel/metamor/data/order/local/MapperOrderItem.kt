package id.co.astra.adel.metamor.data.order.local

import id.co.astra.adel.metamor.domain.order.model.Order

fun Order.mapToEntity() = OrderEntity(
    idItem = idItem,
    nameItem = nameItem,
    priceItem = priceItem,
    quantityItem = quantityItem,
    discountItem = discountItem,
    customerId = customerId
)