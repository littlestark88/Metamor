package id.co.astra.adel.metamor.data.customerorder.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import id.co.astra.adel.metamor.data.customer.local.CustomerEntity
import id.co.astra.adel.metamor.data.order.local.OrderEntity

data class CustomerWithOrder(
    @Embedded
    val customerEntity : CustomerEntity,

    @Relation(
        parentColumn = "customerId",
        entity = OrderEntity::class,
        entityColumn = "orderId",
        associateBy = Junction(
            value = CustomerOrderCrossRefEntity::class,
            parentColumn = "customerId",
            entityColumn = "orderId"
        )
    )
    val orderEntity: List<OrderEntity>
)
