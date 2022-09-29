package id.co.astra.adel.metamor.utils

import id.co.astra.adel.metamor.data.additem.local.AddItemEntity
import id.co.astra.adel.metamor.data.checkout.local.CheckoutEntity
import id.co.astra.adel.metamor.data.checkout.local.mapToItem
import id.co.astra.adel.metamor.data.customer.local.CustomerEntity
import id.co.astra.adel.metamor.data.order.local.OrderEntity
import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderEntity
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.domain.checkout.model.CheckoutList
import id.co.astra.adel.metamor.domain.customer.model.Customer
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrderList

object DataMapper {
    fun mapToAddItemEntityToDomainList(input: List<AddItemEntity>): List<AddItem> =
        input.map {
            AddItem(
                idItem = it.idItem,
                nameItem = it.nameItem.orEmpty(),
                priceItem = it.priceItem ?: 0.0,
                imageItem = it.imageItem
            )
        }

    fun mapToCheckoutEntityToDomainList(input: List<CheckoutEntity>): List<Checkout> =
        input.map {
            Checkout(
                idCustomer = it.idCustomer,
                nameCustomer = it.nameCustomer.orEmpty(),
                numberPhone = it.numberPhone ?: 0,
                email = it.email.orEmpty(),
                total = it.total ?: 0.0,
                ppn = it.ppn ?: 0.0,
                quantity = it.quantity ?: 0,
                typePayment = it.typePayment.orEmpty(),
                cash = it.cash ?: 0.0,
                cashReturn = it.cashReturn ?: 0.0,
                date = it.date.orEmpty(),
                checkoutItem = it.checkoutItem.orEmpty().map { checkoutItem -> checkoutItem.mapToItem() }
            )
        }

    fun mapToCheckoutEntityToDomain(input: CheckoutEntity): Checkout =
        Checkout(
            idCustomer = input.idCustomer,
            nameCustomer = input.nameCustomer.orEmpty(),
            numberPhone = input.numberPhone ?: 0,
            email = input.email.orEmpty(),
            total = input.total ?: 0.0,
            ppn = input.ppn ?: 0.0,
            quantity = input.quantity ?: 0,
            typePayment = input.typePayment.orEmpty(),
            cash = input.cash ?: 0.0,
            cashReturn = input.cashReturn ?: 0.0,
            date = input.date.orEmpty(),
            checkoutItem = input.checkoutItem.orEmpty().map { checkoutItem -> checkoutItem.mapToItem() }
        )

    fun mapToCustomerEntityToDomainList(input: List<CustomerEntity>): List<Customer> =
        input.map {
            Customer(
                idCustomer = it.idCustomer,
                nameCustomer = it.nameCustomer.orEmpty(),
                numberPhone = it.numberPhone ?: 0,
                email = it.email.orEmpty()
            )
        }

    fun mapToOrderEntityToDomainList(input: List<OrderEntity>): List<Order> =
        input.map {
            Order(
                idItem = it.idItem,
                nameItem = it.nameItem.orEmpty(),
                priceItem = it.priceItem ?: 0.0,
                quantityItem = it.quantityItem ?: 0,
                discountItem = it.discountItem ?: 0
            )
        }

    fun mapOrderToCheckoutList(input: List<Order>): List<CheckoutList> =
        input.map {
            CheckoutList(
                idItem = it.idItem,
                nameItem = it.nameItem,
                priceItem = it.priceItem,
                quantityItem = it.quantityItem,
                discountItem = it.discountItem
            )
        }

    fun mapSaveOrderEntityToDomain(input: List<SaveOrderEntity>): List<SaveOrder> =
        input.map {
            SaveOrder(
                idCustomer = it.idCustomer,
                nameCustomer = it.nameCustomer.orEmpty(),
                numberPhone = it.numberPhone ?: 0,
                email = it.email.orEmpty(),
                orderList = it.orderList.map { saveOrderItem -> saveOrderItem.mapToItem() }
            )
        }

    fun mapGetSaveOrderEntityToDomain(input: SaveOrderEntity): SaveOrder =
            SaveOrder(
                idCustomer = input.idCustomer,
                nameCustomer = input.nameCustomer.orEmpty(),
                numberPhone = input.numberPhone ?: 0,
                email = input.email.orEmpty(),
                orderList = input.orderList.map { saveOrderItem -> saveOrderItem.mapToItem() }
            )

    fun mapOrderToSaveOrderList(input: List<Order>): List<SaveOrderList> =
        input.map {
            SaveOrderList(
                idItem = it.idItem,
                nameItem = it.nameItem,
                priceItem = it.priceItem,
                quantityItem = it.quantityItem,
                discountItem = it.discountItem
            )
        }

}
