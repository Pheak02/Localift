package com.local.lift.fragment

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Initialize UI components
        progressBar = findViewById(R.id.progress_bar)
        errorTextView = findViewById(R.id.error_text)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        // Observe ViewModel LiveData
        viewModel.categoryData.observe(this) { apiState ->
            when (apiState.state) {
                State.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    errorTextView.visibility = View.GONE
                }
                State.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                    // Update the UI with the data
                    apiState.data?.let { categories ->
                        // Bind categories to your RecyclerView or layout
                    }
                }
                State.ERROR -> {
                    progressBar.visibility = View.GONE
                    errorTextView.visibility = View.VISIBLE
                    errorTextView.text = apiState.errorMessage
                }
            }
        }

        // Fetch categories
        viewModel.fetchCategories()
    }
}
