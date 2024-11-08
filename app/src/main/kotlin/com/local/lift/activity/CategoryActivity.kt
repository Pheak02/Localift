package com.local.lift.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.local.lift.viewmodel.CategoryViewModel
import com.local.locallift.R

class CategoryActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        // Observe LiveData
        viewModel.productListLiveData.observe(this, Observer { apiState ->
            when (apiState.state) {
                State.LOADING -> {
                    // Show loading indicator
                }
                State.SUCCESS -> {
                    // Display the product list
                    apiState.data?.let { products ->
                        // Populate the product list into the UI
                    }
                }
                State.ERROR -> {
                    // Show error message
                    Toast.makeText(this, apiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Trigger data fetch
        viewModel.fetchProducts()
    }
}
