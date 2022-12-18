package id.co.astra.adel.metamor.data.order.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTakeItem(orderEntity: OrderEntity)

    @Update
    suspend fun updateTakeItem(orderEntity: OrderEntity)

    @Query("DELETE FROM orderEntity WHERE orderId = :idItem")
    suspend fun deleteTakeItem(idItem: Int)

    @Query("SELECT * from orderEntity ORDER BY orderId ASC")
    fun getAllTakeItem(): Flow<List<OrderEntity>>

    @Query("SELECT * from OrderEntity WHERE customerId = :customerId")
    fun getOrderByCustomerId(customerId: Int): Flow<List<OrderEntity>>
}