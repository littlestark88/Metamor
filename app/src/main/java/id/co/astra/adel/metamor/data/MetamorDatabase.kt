package id.co.astra.adel.metamor.data

import androidx.room.*
import id.co.astra.adel.metamor.data.additem.local.AddItemDao
import id.co.astra.adel.metamor.data.additem.local.AddItemEntity
import id.co.astra.adel.metamor.data.checkout.local.CheckoutDao
import id.co.astra.adel.metamor.data.checkout.local.CheckoutEntity
import id.co.astra.adel.metamor.data.customer.local.CustomerDao
import id.co.astra.adel.metamor.data.customer.local.CustomerEntity
import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderCrossRefEntity
import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderDao
import id.co.astra.adel.metamor.data.order.local.OrderDao
import id.co.astra.adel.metamor.data.order.local.OrderEntity
import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderDao
import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderEntity

@Database(entities = [AddItemEntity::class, OrderEntity::class, CustomerEntity::class, CheckoutEntity::class, SaveOrderEntity::class, CustomerOrderCrossRefEntity::class], version = 11)
@TypeConverters(ConverterImage::class, CheckoutConverter::class, OrderConverter::class)
abstract class MetamorDatabase: RoomDatabase() {

    abstract fun addItemDao(): AddItemDao
    abstract fun orderDao(): OrderDao
    abstract fun customerDao(): CustomerDao
    abstract fun customerOrderDao(): CustomerOrderDao
    abstract fun checkoutDao(): CheckoutDao
    abstract fun saveOrderDao(): SaveOrderDao
}