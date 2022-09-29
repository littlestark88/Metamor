package id.co.astra.adel.metamor.data.checkout.local

import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderItem
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.domain.checkout.model.CheckoutList
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrderList

fun Checkout.mapToEntity() = CheckoutEntity(
    idCustomer = idCustomer,
    nameCustomer = nameCustomer,
    numberPhone = numberPhone,
    email = email,
    total = total,
    ppn = ppn,
    quantity = quantity,
    typePayment = typePayment,
    cash = cash,
    cashReturn = cashReturn,
    date = date,
    checkoutItem = checkoutItem.map { it.mapToEntity() }
)

fun CheckoutList.mapToEntity() = CheckoutItem(
    idItem = idItem,
    nameItem = nameItem,
    priceItem = priceItem,
    quantityItem = quantityItem,
    discountItem = discountItem
)

fun CheckoutItem.mapToItem() = CheckoutList(
    idItem = idItem ?: 0,
    nameItem = nameItem.orEmpty(),
    priceItem = priceItem ?: 0.0,
    quantityItem = quantityItem ?: 0,
    discountItem = discountItem ?: 0
)

fun SaveOrderItem.mapToItem() = SaveOrderList(
    idItem = idItem,
    nameItem = nameItem.orEmpty(),
    priceItem = priceItem ?: 0.0,
    quantityItem = quantityItem ?: 0,
    discountItem = discountItem ?: 0
)