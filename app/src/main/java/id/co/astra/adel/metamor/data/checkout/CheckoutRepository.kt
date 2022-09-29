package id.co.astra.adel.metamor.data.checkout

import id.co.astra.adel.metamor.data.checkout.local.mapToEntity
import id.co.astra.adel.metamor.domain.checkout.ICheckoutRepository
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckoutRepository(
    private val checkOutLocalSource: CheckoutLocalSource
): ICheckoutRepository {
    override suspend fun insertCheckout(checkout: Checkout) {
        checkOutLocalSource.insertCheckout(checkout.mapToEntity())
    }

    override suspend fun updateCheckout(checkout: Checkout) {
        checkOutLocalSource.updateCheckout(checkout.mapToEntity())
    }

    override fun deleteCheckout(checkout: Checkout) {
        checkOutLocalSource.deleteCheckout(checkout.mapToEntity())
    }

    override fun getAllCheckout(): Flow<List<Checkout>> {
        return checkOutLocalSource.getAllCheckout().map { DataMapper.mapToCheckoutEntityToDomainList(it) }
    }

    override fun getCheckout(id: Int): Flow<Checkout> {
        return checkOutLocalSource.getCheckout(id).map { DataMapper.mapToCheckoutEntityToDomain(it) }
    }
}