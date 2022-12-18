package id.co.astra.adel.metamor.data.customerorder.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerOrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomerOrder(customerOrderCrossRefEntity: List<CustomerOrderCrossRefEntity>)

    @Transaction
    @Query("SELECT * from CustomerEntity")
    fun getAllCustomerWithOrder(): Flow<List<CustomerWithOrder>>
}