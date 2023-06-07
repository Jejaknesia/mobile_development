package com.vanessaleo.jejaknesia.ui.blog

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ActivityDetailBlogBinding
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class DetailBlogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBlogBinding

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.blog_detail_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val author = intent.getStringExtra(EXTRA_AUTHOR)
        val date = intent.getStringExtra(EXTRA_DATE)
        val content = intent.getStringExtra(EXTRA_CONTENT)
        val photo = intent.getStringExtra(EXTRA_PHOTO)

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        sdf.timeZone = TimeZone.getTimeZone("ICT")
        val time = date?.let { sdf.parse(it)?.time }
        val prettyTime = PrettyTime(Locale.getDefault())
        val timeAsAgo = prettyTime.format(time?.let { Date(it) })


        binding.apply {
            tvDetailTitle.text = title
            tvWrittenBy.text = author
            tvPublishDate.text = timeAsAgo
            tvDetailContent.text = content

            Glide.with(this@DetailBlogActivity)
                .load(photo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivDetailPhoto)
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()

    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_CONTENT = "extra_content"
        const val EXTRA_PHOTO = "extra_photo"
    }
}