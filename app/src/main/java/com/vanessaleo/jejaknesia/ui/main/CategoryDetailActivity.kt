package com.vanessaleo.jejaknesia.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ActivityCategoryDetailBinding

class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.result_detail_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(EXTRA_NAME)
        val city = intent.getStringExtra(EXTRA_CITY)
        val address = intent.getStringExtra(EXTRA_ADDRESS)
        val desc = intent.getStringExtra(EXTRA_DESC)
        val rating = intent.getStringExtra(EXTRA_RATING)
        val image = intent.getStringExtra(EXTRA_IMAGE)


        binding.apply {
            tvDetailName.text = name
            tvDetailCity.text = city
            tvDetailAddress.text = address
            tvDetailDesc.text = desc
            tvDetailRating.text = rating.toString()

            Glide.with(this@CategoryDetailActivity)
                .load(image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivDetailResult)
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()

    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CITY = "extra_city"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_IMAGE = "extra_image"
    }
}