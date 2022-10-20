package id.co.astra.adel.metamor.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.co.astra.adel.metamor.domain.additem.AddItemInteractor
import id.co.astra.adel.metamor.domain.additem.AddItemUseCase
import id.co.astra.adel.metamor.domain.checkout.CheckoutInteractor
import id.co.astra.adel.metamor.domain.checkout.CheckoutUseCase
import id.co.astra.adel.metamor.domain.customer.CustomerInteractor
import id.co.astra.adel.metamor.domain.customer.CustomerUseCase
import id.co.astra.adel.metamor.domain.customerorder.CustomerOrderInteractor
import id.co.astra.adel.metamor.domain.customerorder.CustomerOrderUseCase
import id.co.astra.adel.metamor.domain.customerorder.model.CustomerOrder
import id.co.astra.adel.metamor.domain.order.OrderInteractor
import id.co.astra.adel.metamor.domain.order.OrderUseCase
import id.co.astra.adel.metamor.domain.saveorder.SaveOrderInteractor
import id.co.astra.adel.metamor.domain.saveorder.SaveOrderUseCase

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideAddItemUseCase(addItemInteractor: AddItemInteractor): AddItemUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCheckoutUseCase(checkoutInteractor: CheckoutInteractor): CheckoutUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCustomerUseCase(customerInteractor: CustomerInteractor): CustomerUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCustomerOrderUseCase(customerOrderInteractor: CustomerOrderInteractor): CustomerOrderUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideOrderUseCase(orderInteractor: OrderInteractor): OrderUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideSaveOrderUseCase(saveOrderInteractor: SaveOrderInteractor): SaveOrderUseCase
}