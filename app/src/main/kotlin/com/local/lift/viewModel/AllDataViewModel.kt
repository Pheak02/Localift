package com.local.lift.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.api.ApiManager
import com.local.lift.api.ApiStateProduct
import com.local.lift.api.State
import com.local.lift.model.ProductDataItem
import kotlinx.coroutines.launch

class AllDataViewModel:ViewModel() {
    private val _dataState = MutableLiveData<ApiStateProduct<List<ProductDataItem>>>()
    val dataState: LiveData<ApiStateProduct<List<ProductDataItem>>> get() = _dataState
    fun loadData() {
        val dataService = ApiManager.getDataService()
        viewModelScope.launch {
            try {
                val dataResponse = dataService.loadDataDisplay()
                if(dataResponse.code == 200){
                    _dataState.postValue(ApiStateProduct(State.SUCCESS, dataResponse.data))
                }else{
                    _dataState.postValue(ApiStateProduct(State.ERROR, null))
                }
            }catch (ex: Exception){
                Log.e("ruppite", "Error While loading data: $ex")
            }
        }
    }

}