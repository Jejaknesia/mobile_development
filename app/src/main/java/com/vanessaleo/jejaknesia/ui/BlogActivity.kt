package com.vanessaleo.jejaknesia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.adapter.ListBlogAdapter
import com.vanessaleo.jejaknesia.databinding.ActivityBlogBinding
import com.vanessaleo.jejaknesia.model.Blog

class BlogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlogBinding
    private val list = ArrayList<Blog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.blog_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvBlogs.setHasFixedSize(true)

        list.addAll(getListBlog())
        showRecyclerList()

    }

    private fun showRecyclerList() {
        binding.rvBlogs.layoutManager = LinearLayoutManager(this)

        val listBlogAdapter = ListBlogAdapter(list)
        binding.rvBlogs.adapter = listBlogAdapter


    }

    private fun getListBlog(): ArrayList<Blog> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataAuthor = resources.getStringArray(R.array.data_written_by)
        val dataDate = resources.getStringArray(R.array.data_publish_date)
        val dataContent = resources.getStringArray(R.array.data_content)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)


        val listBlog = ArrayList<Blog>()
        for (i in dataTitle.indices) {
            val blog = Blog(dataTitle[i], dataAuthor[i], dataDate[i],dataContent[i], dataPhoto.getResourceId(i, -1))
            listBlog.add(blog)
        }

        return listBlog
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}