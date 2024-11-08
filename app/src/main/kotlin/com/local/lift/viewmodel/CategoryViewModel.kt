package com.local.lift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.local.lift.model.ApiState
import com.local.lift.model.ApiResponse
import com.local.lift.model.State

class CategoryViewModel : ViewModel() {
    // LiveData to hold the API state and product list
    private val _productListLiveData = MutableLiveData<ApiState<List<Product>>>()
    val productListLiveData: LiveData<ApiState<List<Product>>> get() = _productListLiveData

    // Simulate fetching data from a repository or API
    fun fetchProducts() {
        // Update LiveData to show loading state
        _productListLiveData.value = ApiState(State.loading)

        // Simulate an asynchronous operation (e.g., API call)
        viewModelScope.launch {
            try {
                val products = fetchFromRepository() // Replace with real data source
                _productListLiveData.value = ApiState(State.success, products)
            } catch (e: Exception) {
                _productListLiveData.value = ApiState(State.error, message = e.message)
            }
        }
    }

    // Replace this with real repository call
    private suspend fun fetchFromRepository(): List<Product> {
        delay(2000) // Simulate network delay
        return listOf(
            Product("Crop Bag", 15.99),
            Product("Handbag", 25.49)
        )
    }
}
