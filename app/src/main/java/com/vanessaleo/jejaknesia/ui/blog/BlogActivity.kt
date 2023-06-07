package com.vanessaleo.jejaknesia.ui.blog

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.adapter.ListBlogAdapter
import com.vanessaleo.jejaknesia.auth.LoginViewModel
import com.vanessaleo.jejaknesia.databinding.ActivityBlogBinding
import com.vanessaleo.jejaknesia.model.BlogModel
import com.vanessaleo.jejaknesia.response.DataItem

class BlogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlogBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val blogViewModel: BlogViewModel by viewModels { viewModelFactory }
    private lateinit var blogAdapter: ListBlogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.blog_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupAdapter()
        setupViewModel()
//        setupAction()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapter() {

        blogAdapter = ListBlogAdapter()
        blogAdapter.notifyDataSetChanged()

        blogAdapter.setOnItemClickCallback(object: ListBlogAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val intentToDetail = Intent(this@BlogActivity, DetailBlogActivity::class.java)
                intentToDetail.putExtra(DetailBlogActivity.EXTRA_TITLE, data.title)
                intentToDetail.putExtra(DetailBlogActivity.EXTRA_AUTHOR, data.author)
                intentToDetail.putExtra(DetailBlogActivity.EXTRA_DATE, data.date)
                intentToDetail.putExtra(DetailBlogActivity.EXTRA_CONTENT, data.content)
                intentToDetail.putExtra(DetailBlogActivity.EXTRA_PHOTO, data.photo)

                startActivity(intentToDetail)

            }

        })




    }


    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        binding.apply {
            rvBlogs.layoutManager = LinearLayoutManager(this@BlogActivity)
            rvBlogs.setHasFixedSize(true)
            rvBlogs.adapter = blogAdapter

        }


        blogViewModel.getBlogs().observe(this) { blogs ->
            if(blogs != null) {
                showLoading()
                blogAdapter.setListBlog(blogs)

            }

        }
    }

//    private fun setupAction() {
//        binding.apply {
//            rvBlogs.layoutManager = LinearLayoutManager(this@BlogActivity)
//            rvBlogs.setHasFixedSize(true)
//            rvBlogs.adapter = blogAdapter
//
//
//
//            blogViewModel.apply {
//                dataItem.observe(this@BlogActivity) { blog ->
//                    if(blog != null) {
////                        blogAdapter.setListBlog(blog)
//                        showLoading()
//                    }
//                }
//            }
//
//            blogViewModel.getBlogs()
//        }
//    }

    private fun showLoading() {
        blogViewModel.isLoading.observe(this) { isLoading ->
            if(isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}