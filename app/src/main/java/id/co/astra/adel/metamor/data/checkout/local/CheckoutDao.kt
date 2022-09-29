package id.co.astra.adel.metamor.data.checkout.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCheckout(checkoutEntity: CheckoutEntity)

    @Update
    suspend fun updateCheckout(checkoutEntity: CheckoutEntity)

    @Delete
    fun deleteCheckout(checkoutEntity: CheckoutEntity)

    @Query("SELECT * from checkoutEntity ORDER BY idCustomer ASC")
    fun getAllCheckout(): Flow<List<CheckoutEntity>>

    @Query("SELECT * from checkoutEntity WHERE idCustomer =:id")
    fun getCheckoutById(id: Int): Flow<CheckoutEntity>
}