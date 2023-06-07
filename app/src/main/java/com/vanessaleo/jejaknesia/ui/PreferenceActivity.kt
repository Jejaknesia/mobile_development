package com.vanessaleo.jejaknesia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferenceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.preference)

        binding.apply {

            btnChosenCategory.setOnClickListener {
                val atmosphere = atmosphere.checkedRadioButtonId == R.id.rb_popular
                val typical = typical.checkedRadioButtonId == R.id.typical


            }
        }
    }
}