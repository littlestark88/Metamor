package id.co.astra.adel.metamor.presentasion.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.domain.additem.AddItemUseCase
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(private val addItemUseCase: AddItemUseCase): ViewModel() {

    val getAllItem = addItemUseCase.getAllAddItem().asLiveData()

    fun getDataByName(nameItem: String) = addItemUseCase.getDataByName(nameItem).asLiveData()

    suspend fun insertItem(addItemEntity: AddItem) {
        viewModelScope.launch {
            addItemUseCase.insertAddItem(addItemEntity)
        }
    }

    suspend fun updateItem(addItemEntity: AddItem) {
        viewModelScope.launch {
            addItemUseCase.updateAddItem(addItemEntity)
        }
    }

    suspend fun deleteItem(idItem: Int) {
        viewModelScope.launch {
            addItemUseCase.deleteAddItem(idItem)
        }
    }
}