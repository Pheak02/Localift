package com.local.lift.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.local.lift.api.ApiHome
import com.local.lift.model.ApiState
import com.local.lift.model.CategoryProductHome
import com.local.lift.model.HomepageData
import com.local.lift.model.ProductDetailHome
import com.local.lift.model.State
import kotlinx.coroutines.launch
import java.lang.Exception

class HomePageViewModel : ViewModel() {

    private val apiHome = ApiHome.create()

    private val _homepageState = MutableLiveData<ApiState<List<HomepageData>>>()
    val homepageState: LiveData<ApiState<List<HomepageData>>> get() = _homepageState

    fun loadHomePageData() {
        Log.d("HomePageViewModel", "Starting to load home page data...")
        _homepageState.postValue(ApiState(State.loading, null))

        viewModelScope.launch {
            try {
                // Fetching homepage data from the API
                Log.d("HomePageViewModel", "Making API request to fetch home page data...")
                val homePageResponse = apiHome.homePage()

                // Log the API response for debugging
                Log.d("HomePageViewModel", "API response: $homePageResponse")

                if ( homePageResponse.data != null) {
                    Log.d("HomePageViewModel", "API data loaded successfully")

                    // Mapping the response to HomepageData objects
                    val dataList = homePageResponse.data.map { data ->
                        HomepageData(
                            categoryProductHome = CategoryProductHome(
                                categoryName = data.categoryProductHome.categoryName,
                                categoryImage = data.categoryProductHome.categoryImage
                            ),
                            productDetailHome = ProductDetailHome(
                                productImage = data.productDetailHome.productImage,
                                productName = data.productDetailHome.productName,
                                productPrice = data.productDetailHome.productPrice
                            ),
//                            status = homePageResponse.status
                        )
                    }

                    // Log the data list
                    Log.d("HomePageViewModel", "Mapped data list: $dataList")

                    // Post success state with the correct data and status
                    _homepageState.postValue(ApiState(State.success, dataList, status = homePageResponse.status))
                } else {
                    Log.d("HomePageViewModel", "Failed to load data: ${homePageResponse.status}")
                    _homepageState.postValue(ApiState(State.error, null, status = homePageResponse.status ?: "Failed to load data"))
                }
            } catch (e: Exception) {
                Log.e("HomePageViewModel", "Error fetching data: ${e.message}")
                _homepageState.postValue(ApiState(State.error, null, status = e.message ?: "Unknown error"))
            }
        }
    }
}
