package id.co.astra.adel.metamor.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.co.astra.adel.metamor.data.additem.AddItemRepository
import id.co.astra.adel.metamor.data.checkout.CheckoutRepository
import id.co.astra.adel.metamor.data.customer.CustomerRepository
import id.co.astra.adel.metamor.data.order.OrderRepository
import id.co.astra.adel.metamor.data.saveorder.SaveOrderRepository
import id.co.astra.adel.metamor.domain.additem.IAddItemRepository
import id.co.astra.adel.metamor.domain.checkout.ICheckoutRepository
import id.co.astra.adel.metamor.domain.customer.ICustomerRepository
import id.co.astra.adel.metamor.domain.order.IOrderRepository
import id.co.astra.adel.metamor.domain.saveorder.ISaveOrderRepository

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAddItemRepository(addItemRepository: AddItemRepository): IAddItemRepository

    @Binds
    abstract fun provideCheckoutRepository(checkoutRepository: CheckoutRepository): ICheckoutRepository

    @Binds
    abstract fun provideCustomerRepository(customerRepository: CustomerRepository): ICustomerRepository

    @Binds
    abstract fun provideOrderRepository(orderRepository: OrderRepository): IOrderRepository

    @Binds
    abstract fun provideSaveOrderRepository(saveOrderRepository: SaveOrderRepository): ISaveOrderRepository
}