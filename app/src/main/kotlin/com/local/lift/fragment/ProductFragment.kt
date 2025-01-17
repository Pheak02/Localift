package com.local.lift.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.local.lift.adapter.CategoryAdapter
import com.local.lift.adapter.ProductAdapter
import com.local.lift.model.CategoryProductHome
import com.local.lift.model.ProductDetailHome
import com.local.lift.model.State
import com.local.lift.viewModel.HomePageViewModel
import com.local.locallift.R
import com.local.locallift.databinding.HomePageBinding

class ProductFragment : Fragment() {

    private var _binding: HomePageBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    private val categoryList = mutableListOf<CategoryProductHome>()
    private val productList = mutableListOf<ProductDetailHome>()

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomePageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        loadData()

        // Navigation setup
        binding.viewCategory.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_category1Fragment)
        }

        binding.goToCart.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_shoppingCartFragment)
        }

        binding.userProfile.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_userProfileFragment)
        }
    }

    private fun setupRecyclerViews() {
        Log.d("RecyclerView", "Initializing RecyclerView for categories and products")
        // Log category and product list sizes
        Log.d("RecyclerView", "Category list size: ${categoryList.size}")
        Log.d("RecyclerView", "Product list size: ${productList.size}")
        Log.d("RecyclerView", "Category List: $categoryList")
        Log.d("RecyclerView", "Product List: $productList")

        binding.recyclerViewCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter(categoryList)
        binding.recyclerViewCategory.adapter = categoryAdapter

        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter(productList)
        binding.recyclerViewProduct.adapter = productAdapter
    }

    private fun loadData() {
        // Loading data from ViewModel and API
        viewModel.loadHomePageData()

        viewModel.homepageState.observe(viewLifecycleOwner, { state ->
            when (state.state) {
                State.loading -> {
                    // Optionally show a loading indicator
                }
                State.success -> {
                    state.data?.let { homepageData ->
                        categoryList.clear()
                        productList.clear()

                        homepageData.forEach { data ->
                            // Adding category and product to respective lists
                            categoryList.add(data.categoryProductHome)
                            productList.add(data.productDetailHome)
                        }

                        // Notify adapters for UI update
                        categoryAdapter.notifyDataSetChanged()
                        productAdapter.notifyDataSetChanged()
                    }
                }
                State.error -> {
                    Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
