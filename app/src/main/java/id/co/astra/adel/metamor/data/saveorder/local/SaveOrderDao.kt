package id.co.astra.adel.metamor.data.saveorder.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SaveOrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaveOrder(saveOrderEntity: SaveOrderEntity)

    @Update
    suspend fun updateSaveOrder(saveOrderEntity: SaveOrderEntity)

    @Query("DELETE FROM SaveOrderEntity WHERE idCustomer = :idCustomer")
    suspend fun deleteSaveOrder(idCustomer: Int)

    @Query("SELECT * from SaveOrderEntity ORDER BY idCustomer ASC")
    fun getSaveOrder(): Flow<List<SaveOrderEntity>>

    @Query("SELECT * from SaveOrderEntity WHERE idCustomer = :idCustomer")
    fun getSaveOrderById(idCustomer: Int): Flow<SaveOrderEntity>
}