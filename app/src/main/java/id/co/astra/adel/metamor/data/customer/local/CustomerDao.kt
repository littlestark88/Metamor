package id.co.astra.adel.metamor.data.customer.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomer(customerEntity: CustomerEntity)

    @Update
    suspend fun updateCustomer(customerEntity: CustomerEntity)

    @Query("DELETE FROM customerEntity WHERE customerId =  :idCustomer")
    suspend fun deleteCustomer(idCustomer: Int)

    @Query("SELECT * from customerEntity ORDER BY customerId ASC")
    fun getAllCustomer(): Flow<List<CustomerEntity>>
}