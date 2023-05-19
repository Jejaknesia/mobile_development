package com.vanessaleo.jejaknesia.start_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.auth.RegisterActivity
import com.vanessaleo.jejaknesia.databinding.ActivityStartScreenBinding

class StartScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this@StartScreenActivity, RegisterActivity::class.java))
        }

        supportActionBar?.hide()
    }
}