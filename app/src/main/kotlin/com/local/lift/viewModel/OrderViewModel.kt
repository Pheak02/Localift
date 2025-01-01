package com.local.lift.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.api.ApiManager
import com.local.lift.model.ApiState
import com.local.lift.model.Order
import com.local.lift.model.State
import com.local.locallift.api.APIState
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    private val _orderState = MutableLiveData<ApiState<List<Order>>>()
    val orderState: LiveData<ApiState<List<Order>>> get() = _orderState

    fun loadOrders() {
        val apiService = ApiManager.getApiService()
        _orderState.postValue(ApiState(State.loading, null))

        viewModelScope.launch {
            try {
                val orderResponse = apiService.loadOrders()
                if (orderResponse.isSuccess()) {
                    _orderState.postValue(ApiState(State.success, orderResponse.data))
                } else {
                    _orderState.postValue(ApiState(State.error, null))
                }
            } catch (e: Exception) {
                _orderState.postValue(ApiState(State.error, null))
            }
        }
    }

}