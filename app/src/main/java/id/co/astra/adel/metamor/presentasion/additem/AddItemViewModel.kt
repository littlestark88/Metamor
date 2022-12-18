package id.co.astra.adel.metamor.presentasion.additem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.base.Resource
import id.co.astra.adel.metamor.domain.additem.AddItemUseCase
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(private val addItemUseCase: AddItemUseCase) :
    ViewModel() {

    val getAllItem: LiveData<Resource<List<AddItem>>> by lazy { _getAllItem }
    private val _getAllItem = MutableLiveData<Resource<List<AddItem>>>()

    val getDataByName: LiveData<Resource<List<AddItem>>> by lazy { _getDataByName }
    private val _getDataByName = MutableLiveData<Resource<List<AddItem>>>()

    val insertItem: LiveData<Resource<Unit>> by lazy { _insertItem }
    private val _insertItem = MutableLiveData<Resource<Unit>>()

    val updateItem: LiveData<Resource<Unit>> by lazy { _updateItem }
    private val _updateItem = MutableLiveData<Resource<Unit>>()

    val deleteItem: LiveData<Resource<Unit>> by lazy { _deleteItem }
    private val _deleteItem = MutableLiveData<Resource<Unit>>()

    fun getAllItem() {
        _getAllItem.value = Resource.Loading()

        viewModelScope.launch {
            addItemUseCase.getAllAddItem()
                .catch {
                    _getAllItem.value = Resource.Error(it.message.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        _getAllItem.value = Resource.Empty()
                    } else {
                        _getAllItem.value = Resource.Success(it)
                    }
                }
        }
    }

    fun getDataByName(nameItem: String) {
        _getDataByName.value = Resource.Loading()

        viewModelScope.launch {
            addItemUseCase.getDataByName(nameItem)
                .catch {
                    _getDataByName.value = Resource.Error(it.message.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        _getDataByName.value = Resource.Empty()
                    } else {
                        _getDataByName.value = Resource.Success(it)
                    }
                }
        }
    }

    suspend fun insertItem(addItemEntity: AddItem) {
        _insertItem.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                addItemUseCase.insertAddItem(addItemEntity)
            }.onSuccess {
                _insertItem.value = Resource.Success(Unit)
            }.onFailure {
                _insertItem.value = Resource.Error(it.message.toString())
            }
        }
    }

    suspend fun updateItem(addItemEntity: AddItem) {
        _updateItem.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                addItemUseCase.updateAddItem(addItemEntity)
            }.onSuccess {
                _updateItem.value = Resource.Success(Unit)
            }.onFailure {
                _updateItem.value = Resource.Error(it.message.toString())
            }
        }
    }

    suspend fun deleteItem(idItem: Int) {
        _deleteItem.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                addItemUseCase.deleteAddItem(idItem)
            }.onSuccess {
                _deleteItem.value = Resource.Success(Unit)
            }.onFailure {
                _deleteItem.value = Resource.Error(it.message.toString())
            }
        }
    }
}