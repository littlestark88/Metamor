package id.co.astra.adel.metamor.presentasion.saveorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.domain.saveorder.SaveOrderUseCase
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import javax.inject.Inject

@HiltViewModel
class SaveOrderViewModel @Inject constructor(private val saveOrderUseCase: SaveOrderUseCase): ViewModel() {
    val getSaveOrder = saveOrderUseCase.getSaveOrder().asLiveData()

    fun getSaveOrderById(idCustomer: Int) = saveOrderUseCase.getSaveOrderById(idCustomer).asLiveData()

    suspend fun insertSaveOrder(saveOrder: SaveOrder) {
        saveOrderUseCase.insertSaveOrder(saveOrder)
    }

    suspend fun updateSaveOrder(saveOrder: SaveOrder) {
        saveOrderUseCase.updateSaveOrder(saveOrder)
    }

    suspend fun deleteSaveOrder(idCustomer: Int) {
        saveOrderUseCase.deleteSaveOrder(idCustomer)
    }
}