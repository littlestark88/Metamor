package id.co.astra.adel.metamor.presentasion.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.base.Resource
import id.co.astra.adel.metamor.domain.order.OrderUseCase
import id.co.astra.adel.metamor.domain.order.model.Order
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderUseCase: OrderUseCase) : ViewModel() {

    val getAllOrder: LiveData<Resource<List<Order>>> by lazy { _getAllOrder }
    private val _getAllOrder = MutableLiveData<Resource<List<Order>>>()

    val insertOrder: LiveData<Resource<Unit>> by lazy { _insertOrder }
    private val _insertOrder = MutableLiveData<Resource<Unit>>()

    val updateOrder: LiveData<Resource<Unit>> by lazy { _updateOrder }
    private val _updateOrder = MutableLiveData<Resource<Unit>>()

    val deleteOrder: LiveData<Resource<Unit>> by lazy { _deleteOrder }
    private val _deleteOrder = MutableLiveData<Resource<Unit>>()

    val getOrderByCustomerId: LiveData<Resource<List<Order>>> by lazy { _getOrderByCustomerId }
    private val _getOrderByCustomerId = MutableLiveData<Resource<List<Order>>>()

    fun getAllOrder() {
        _getAllOrder.value = Resource.Loading()

        viewModelScope.launch {
            orderUseCase.getAllOrder()
                .catch {
                    _getAllOrder.value = Resource.Error(it.message.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        _getAllOrder.value = Resource.Empty()
                    } else {
                        _getAllOrder.value = Resource.Success(it)
                    }
                }
        }
    }

    suspend fun insertOrder(order: Order) {
        _insertOrder.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                orderUseCase.insertOrder(order)
            }.onSuccess {
                _insertOrder.value = Resource.Success(Unit)
            }.onFailure {
                _insertOrder.value = Resource.Error(it.message.toString())
            }
        }
    }

    suspend fun updateCustomer(order: Order) {
        _updateOrder.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                orderUseCase.updateOrder(order)
            }.onSuccess {
                _updateOrder.value = Resource.Success(Unit)
            }.onFailure {
                _updateOrder.value = Resource.Error(it.message.toString())
            }
        }
    }

    suspend fun deleteCustomer(idItem: Int) {
        _deleteOrder.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                orderUseCase.deleteOrder(idItem)
            }.onSuccess {
                _deleteOrder.value = Resource.Success(Unit)
            }.onFailure {
                _deleteOrder.value = Resource.Error(it.message.toString())
            }
        }
    }

    fun getOrderByCustomerId(customerId: Int) {
        _getOrderByCustomerId.value = Resource.Loading()

        viewModelScope.launch {
            orderUseCase.getOrderByCustomerId(customerId)
                .catch {
                    _getOrderByCustomerId.value = Resource.Error(it.message.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        _getOrderByCustomerId.value = Resource.Empty()
                    } else {
                        _getOrderByCustomerId.value = Resource.Success(it)
                    }
                }
        }
    }
}