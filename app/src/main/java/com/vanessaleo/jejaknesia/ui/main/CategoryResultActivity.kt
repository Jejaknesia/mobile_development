package com.vanessaleo.jejaknesia.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.adapter.ListDataAdapter
import com.vanessaleo.jejaknesia.databinding.ActivityCategoryResultBinding
import com.vanessaleo.jejaknesia.model.DataModel
import com.vanessaleo.jejaknesia.response.DataItemItem


class CategoryResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryResultBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var dataAdapter: ListDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.generate_result_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        setupAdapter()
        setupViewModel()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapter() {

        dataAdapter = ListDataAdapter()
        dataAdapter.notifyDataSetChanged()

        dataAdapter.setOnItemClickCallback(object : ListDataAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItemItem) {
                val intentToDetail =
                    Intent(this@CategoryResultActivity, CategoryDetailActivity::class.java)
                intentToDetail.putExtra(CategoryDetailActivity.EXTRA_NAME, data.placeName)
                intentToDetail.putExtra(CategoryDetailActivity.EXTRA_CITY, data.city)
                intentToDetail.putExtra(CategoryDetailActivity.EXTRA_ADDRESS, data.address)
                intentToDetail.putExtra(CategoryDetailActivity.EXTRA_DESC, data.descPlace)
                intentToDetail.putExtra(CategoryDetailActivity.EXTRA_RATING, data.rating.toString())
                intentToDetail.putExtra(CategoryDetailActivity.EXTRA_IMAGE, data.imageUrl)

                startActivity(intentToDetail)

            }
        })
    }


    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        showLoading()

        binding.apply {
            rvDataPlace.layoutManager = LinearLayoutManager(this@CategoryResultActivity)
            rvDataPlace.setHasFixedSize(true)
            rvDataPlace.adapter = dataAdapter
        }


        mainViewModel.dataItemItem.observe(this) { data ->
            if (data != null) {
                showLoading()
                dataAdapter.setListData(data)
            }
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