package id.co.astra.adel.metamor.presentasion.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.base.Resource
import id.co.astra.adel.metamor.domain.customer.CustomerUseCase
import id.co.astra.adel.metamor.domain.customer.model.Customer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerUseCase: CustomerUseCase) :
    ViewModel() {

    val getAllCustomer: LiveData<Resource<List<Customer>>> by lazy { _getAllCustomer }
    private val _getAllCustomer = MutableLiveData<Resource<List<Customer>>>()

    val insertCustomer: LiveData<Resource<Unit>> by lazy { _insertCustomer }
    private val _insertCustomer = MutableLiveData<Resource<Unit>>()

    val updateCustomer: LiveData<Resource<Unit>> by lazy { _updateCustomer }
    private val _updateCustomer = MutableLiveData<Resource<Unit>>()

    val deleteCustomer: LiveData<Resource<Unit>> by lazy { _deleteCustomer }
    private val _deleteCustomer = MutableLiveData<Resource<Unit>>()

    fun getAllCustomer() {
        _getAllCustomer.value = Resource.Loading()

        viewModelScope.launch {
            customerUseCase.getAllCustomer()
                .catch {
                    _getAllCustomer.value = Resource.Error(it.message.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        _getAllCustomer.value = Resource.Empty()
                    } else {
                        _getAllCustomer.value = Resource.Success(it)
                    }
                }
        }
    }

    suspend fun insertCustomer(customer: Customer) {
        _insertCustomer.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                customerUseCase.insertCustomer(customer)
            }.onSuccess {
                _insertCustomer.value = Resource.Success(Unit)
            }.onFailure {
                _insertCustomer.value = Resource.Error(it.message.toString())
            }
        }
    }

    suspend fun updateCustomer(customer: Customer) {
        _updateCustomer.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                customerUseCase.updateCustomer(customer)
            }.onSuccess {
                _updateCustomer.value = Resource.Success(Unit)
            }.onFailure {
                _updateCustomer.value = Resource.Error(it.message.toString())
            }
        }
    }

    suspend fun deleteCustomer(idCustomer: Int) {
        _deleteCustomer.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                customerUseCase.deleteCustomer(idCustomer)
            }.onSuccess {
                _deleteCustomer.value = Resource.Success(Unit)
            }.onFailure {
                _deleteCustomer.value = Resource.Error(it.message.toString())
            }
        }
    }
}