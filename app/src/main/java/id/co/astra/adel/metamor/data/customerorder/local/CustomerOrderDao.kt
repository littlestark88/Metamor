package id.co.astra.adel.metamor.data.customerorder.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CustomerOrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomerOrder(customerOrderCrossRefEntity: List<CustomerOrderCrossRefEntity>)
}