package id.co.astra.adel.metamor.di

import androidx.room.Room
import id.co.astra.adel.metamor.data.MetamorDatabase
import id.co.astra.adel.metamor.data.additem.AddItemLocalSource
import id.co.astra.adel.metamor.data.additem.AddItemRepository
import id.co.astra.adel.metamor.data.checkout.CheckoutLocalSource
import id.co.astra.adel.metamor.data.checkout.CheckoutRepository
import id.co.astra.adel.metamor.data.customer.CustomerLocalSource
import id.co.astra.adel.metamor.data.customer.CustomerRepository
import id.co.astra.adel.metamor.data.order.OrderLocalSource
import id.co.astra.adel.metamor.data.order.OrderRepository
import id.co.astra.adel.metamor.data.saveorder.SaveOrderLocalSource
import id.co.astra.adel.metamor.data.saveorder.SaveOrderRepository
import id.co.astra.adel.metamor.domain.additem.AddItemInteractor
import id.co.astra.adel.metamor.domain.additem.AddItemUseCase
import id.co.astra.adel.metamor.domain.additem.IAddItemRepository
import id.co.astra.adel.metamor.domain.checkout.CheckoutInteractor
import id.co.astra.adel.metamor.domain.checkout.CheckoutUseCase
import id.co.astra.adel.metamor.domain.checkout.ICheckoutRepository
import id.co.astra.adel.metamor.domain.customer.CustomerInteractor
import id.co.astra.adel.metamor.domain.customer.CustomerUseCase
import id.co.astra.adel.metamor.domain.customer.ICustomerRepository
import id.co.astra.adel.metamor.domain.order.IOrderRepository
import id.co.astra.adel.metamor.domain.order.OrderInteractor
import id.co.astra.adel.metamor.domain.order.OrderUseCase
import id.co.astra.adel.metamor.domain.saveorder.ISaveOrderRepository
import id.co.astra.adel.metamor.domain.saveorder.SaveOrderInteractor
import id.co.astra.adel.metamor.domain.saveorder.SaveOrderUseCase
import id.co.astra.adel.metamor.presentasion.additem.AddItemViewModel
import id.co.astra.adel.metamor.presentasion.customer.CustomerViewModel
import id.co.astra.adel.metamor.presentasion.order.OrderViewModel
import id.co.astra.adel.metamor.presentasion.checkout.CheckoutViewModel
import id.co.astra.adel.metamor.presentasion.saveorder.SaveOrderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    factory { get<MetamorDatabase>().addItemDao() }
    factory { get<MetamorDatabase>().checkoutDao() }
    factory { get<MetamorDatabase>().customerDao() }
    factory { get<MetamorDatabase>().orderDao() }
    factory { get<MetamorDatabase>().saveOrderDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MetamorDatabase::class.java, "metamor_database"
        ).fallbackToDestructiveMigration().build()
    }
}

val repository = module {
    single { AddItemLocalSource(get())}
    single { CheckoutLocalSource(get()) }
    single { CustomerLocalSource(get()) }
    single { OrderLocalSource(get()) }
    single { SaveOrderLocalSource(get())}

    single<IAddItemRepository> { AddItemRepository(get()) }
    single<ICheckoutRepository> { CheckoutRepository(get()) }
    single<ICustomerRepository> { CustomerRepository(get()) }
    single<IOrderRepository> { OrderRepository(get()) }
    single<ISaveOrderRepository> { SaveOrderRepository(get()) }
}

val useCaseModule = module {
    factory<AddItemUseCase> { AddItemInteractor(get()) }
    factory<CheckoutUseCase> { CheckoutInteractor(get()) }
    factory<CustomerUseCase> { CustomerInteractor(get()) }
    factory<OrderUseCase> { OrderInteractor(get()) }
    factory<SaveOrderUseCase> { SaveOrderInteractor(get()) }
}

val viewModelModule = module {
    viewModel { AddItemViewModel(get()) }
    viewModel { CheckoutViewModel(get()) }
    viewModel { CustomerViewModel(get()) }
    viewModel { OrderViewModel(get()) }
    viewModel { SaveOrderViewModel(get()) }
}