package id.co.astra.adel.metamor.presentasion.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.astra.adel.metamor.base.Resource
import id.co.astra.adel.metamor.domain.checkout.CheckoutUseCase
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val checkoutUseCase: CheckoutUseCase) :
    ViewModel() {

    val getAllCheckout: LiveData<Resource<List<Checkout>>> by lazy { _getAllCheckout }
    private val _getAllCheckout = MutableLiveData<Resource<List<Checkout>>>()

    val getCheckoutId: LiveData<Resource<Checkout>> by lazy { _getCheckoutId }
    private val _getCheckoutId = MutableLiveData<Resource<Checkout>>()

    val insertCheckout: LiveData<Resource<Unit>> by lazy { _insertCheckout }
    private val _insertCheckout = MutableLiveData<Resource<Unit>>()

    val updateCheckout: LiveData<Resource<Unit>> by lazy { _updateCheckout }
    private val _updateCheckout = MutableLiveData<Resource<Unit>>()

    val deleteCheckout: LiveData<Resource<Unit>> by lazy { _deleteCheckout }
    private val _deleteCheckout = MutableLiveData<Resource<Unit>>()

    fun getAllCheckout() {
        _getAllCheckout.value = Resource.Loading()

        viewModelScope.launch {
            checkoutUseCase.getAllCheckout()
                .catch {
                    _getAllCheckout.value = Resource.Error(it.message.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        _getAllCheckout.value = Resource.Empty()
                    } else {
                        _getAllCheckout.value = Resource.Success(it)
                    }
                }
        }
    }

    suspend fun insertCheckout(checkout: Checkout) {
        _insertCheckout.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                checkoutUseCase.insertCheckout(checkout)
            }.onSuccess {
                _insertCheckout.value = Resource.Success(Unit)
            }.onFailure {
                _insertCheckout.value = Resource.Error(it.message.toString())
            }

        }
    }

    fun getCheckoutById(id: Int) {
        _getCheckoutId.value = Resource.Loading()

        viewModelScope.launch {
            checkoutUseCase.getCheckout(id)
                .catch {
                    _getCheckoutId.value = Resource.Error(it.message.toString())
                }
                .collect {
                    _getCheckoutId.value = Resource.Success(it)
                }
        }
    }


    suspend fun updateCheckout(checkout: Checkout) {
        _updateCheckout.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                checkoutUseCase.updateCheckout(checkout)
            }.onSuccess {
                _updateCheckout.value = Resource.Success(Unit)
            }.onFailure {
                _updateCheckout.value = Resource.Error(it.message.toString())
            }
        }
    }

    fun deleteCheckout(checkout: Checkout) {
        _deleteCheckout.value = Resource.Loading()

        viewModelScope.launch {
            runCatching {
                checkoutUseCase.deleteCheckout(checkout)
            }.onSuccess {
                _deleteCheckout.value = Resource.Success(Unit)
            }.onFailure {
                _deleteCheckout.value = Resource.Error(it.message.toString())
            }
        }
    }
}