package id.co.astra.adel.metamor.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.co.astra.adel.metamor.data.MetamorDatabase
import id.co.astra.adel.metamor.data.additem.local.AddItemDao
import id.co.astra.adel.metamor.data.checkout.local.CheckoutDao
import id.co.astra.adel.metamor.data.customer.local.CustomerDao
import id.co.astra.adel.metamor.data.customerorder.local.CustomerOrderDao
import id.co.astra.adel.metamor.data.order.local.OrderDao
import id.co.astra.adel.metamor.data.saveorder.local.SaveOrderDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MetamorDatabase = Room.databaseBuilder(
        context,
        MetamorDatabase::class.java, "Metamor.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideAddItemDao(database: MetamorDatabase): AddItemDao = database.addItemDao()

    @Provides
    fun provideCheckoutDao(database: MetamorDatabase): CheckoutDao = database.checkoutDao()

    @Provides
    fun provideCustomerDao(database: MetamorDatabase): CustomerDao = database.customerDao()

    @Provides
    fun provideCustomerOrderDao(database: MetamorDatabase): CustomerOrderDao = database.customerOrderDao()

    @Provides
    fun provideOrderDao(database: MetamorDatabase): OrderDao = database.orderDao()

    @Provides
    fun provideSaveOrderDao(database: MetamorDatabase): SaveOrderDao = database.saveOrderDao()
}