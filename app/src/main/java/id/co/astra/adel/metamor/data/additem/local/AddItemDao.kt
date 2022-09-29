package id.co.astra.adel.metamor.data.additem.local

import androidx.room.*
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddItem(addItemEntity: AddItemEntity)

    @Update
    suspend fun updateAddItem(addItemEntity: AddItemEntity)

    @Query("DELETE FROM AddItemEntity WHERE idItem = :idItem")
    suspend fun deleteAddItem(idItem: Int)

    @Query("SELECT * from AddItemEntity ORDER BY idItem ASC")
    fun getAllAddItem(): Flow<List<AddItemEntity>>

    @Query("SELECT * from AddItemEntity WHERE nameItem LIKE '%' || :nameItem || '%' ORDER BY idItem DESC")
    fun getDataByName(nameItem: String): Flow<List<AddItemEntity>>

}