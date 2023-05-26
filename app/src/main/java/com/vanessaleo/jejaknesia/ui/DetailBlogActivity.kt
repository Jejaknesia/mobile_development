package com.vanessaleo.jejaknesia.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ActivityDetailBlogBinding
import com.vanessaleo.jejaknesia.model.Blog

class DetailBlogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBlogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.blog_detail_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataBlog = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_BLOG, Blog::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_BLOG)
        }

        if (dataBlog != null) {
            binding.tvDetailTitle.text = dataBlog.title
            binding.tvWrittenBy.text = dataBlog.author
            binding.tvPublishDate.text = dataBlog.date
            binding.tvDetailContent.text = dataBlog.content
            binding.imgDetailPhoto.setImageResource(dataBlog.photo)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val KEY_BLOG = "key_blog"
    }
}