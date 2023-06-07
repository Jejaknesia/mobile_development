package com.vanessaleo.jejaknesia.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.auth.LoginActivity
import com.vanessaleo.jejaknesia.ui.main.MainActivity
import com.vanessaleo.jejaknesia.ui.main.MainViewModel

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed({
            setupViewModel()
        }, timer.toLong())

    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        mainViewModel.getUser().observe(this) { user ->
            if (user.isLogin) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                finish()
            }
        }
    }


    companion object {
        const val timer = 5000
    }
}