package id.co.astra.adel.metamor.data.checkout

import id.co.astra.adel.metamor.data.checkout.local.CheckoutDao
import id.co.astra.adel.metamor.data.checkout.local.CheckoutEntity
import kotlinx.coroutines.flow.Flow

class CheckoutLocalSource(private val checkoutDao: CheckoutDao): ICheckoutLocalSource {
    override suspend fun insertCheckout(checkoutEntity: CheckoutEntity) = checkoutDao.insertCheckout(checkoutEntity)

    override suspend fun updateCheckout(checkoutEntity: CheckoutEntity) = checkoutDao.updateCheckout(checkoutEntity)

    override fun deleteCheckout(checkoutEntity: CheckoutEntity) = checkoutDao.deleteCheckout(checkoutEntity)

    override fun getAllCheckout(): Flow<List<CheckoutEntity>> = checkoutDao.getAllCheckout()

    override fun getCheckout(id: Int): Flow<CheckoutEntity> = checkoutDao.getCheckoutById(id)
}