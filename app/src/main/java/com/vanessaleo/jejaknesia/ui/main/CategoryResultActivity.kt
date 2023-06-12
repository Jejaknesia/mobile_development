package com.vanessaleo.jejaknesia.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.databinding.ActivityCategoryResultBinding

class CategoryResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryResultBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "CategoryResult"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModelFactory = ViewModelFactory.getInstance(this)

        showLoading()
        mainViewModel.dataResponse.observe(this) {
            Log.d("MainActivity", it.toString())

            val string = it.result.joinToString(separator = "\n")
            binding.result.text = string.toString()
        }
    }

    private fun showLoading() {
        mainViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()

    }
}