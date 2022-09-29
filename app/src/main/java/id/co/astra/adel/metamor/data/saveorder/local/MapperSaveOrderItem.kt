package id.co.astra.adel.metamor.data.saveorder.local

import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrderList

fun SaveOrder.mapToEntity() = SaveOrderEntity(
    idCustomer = idCustomer,
    nameCustomer = nameCustomer,
    numberPhone = numberPhone,
    email = email,
    orderList = orderList.map { it.mapToEntity() }
)

fun SaveOrderList.mapToEntity() = SaveOrderItem(
    idItem = idItem,
    nameItem = nameItem,
    priceItem = priceItem,
    quantityItem = quantityItem,
    discountItem = discountItem
)