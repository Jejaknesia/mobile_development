package com.vanessaleo.jejaknesia.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.auth.LoginActivity
import com.vanessaleo.jejaknesia.databinding.ActivityMainBinding
import com.vanessaleo.jejaknesia.model.DataModel
import com.vanessaleo.jejaknesia.ui.DarkModeActivity
import com.vanessaleo.jejaknesia.ui.blog.BlogActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.home_page)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()


    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        mainViewModel.getUser().observe(this@MainActivity) { user ->
            if (user.isLogin) {
                binding.welcomeName.text = getString(R.string.welcome_name, user.name)
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }


    }

    private fun setupAction() {
        binding.radioGroupOptions.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            var data = String()

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.tempat_bersejarah -> {
                        data = binding.tempatBersejarah.text.toString()
                    }
                    R.id.relaxing -> {
                        data = binding.relaxing.text.toString()
                    }
                    R.id.aktivitas_air -> {
                        data = binding.aktivitasAir.text.toString()
                    }

                    R.id.berkeliling_kota -> {
                        data = binding.berkelilingKota.text.toString()
                    }

                    R.id.seni_dan_budaya -> {
                        data = binding.seniDanBudaya.text.toString()
                    }

                    R.id.hutan_flora -> {
                        data = binding.hutanFlora.text.toString()
                    }

                    R.id.ruang_terbuka -> {
                        data = binding.ruangTerbuka.text.toString()
                    }
                }

                binding.btnChosenCategory.setOnClickListener {
                    mainViewModel.postData(DataModel(data))
                    startActivity(Intent(this@MainActivity, CategoryResultActivity::class.java))

                }

            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_blog -> {
                val blogPageIntent = Intent(this@MainActivity, BlogActivity::class.java)
                startActivity(blogPageIntent)
            }

            R.id.option_dark_mode -> {
                Intent(this@MainActivity, DarkModeActivity::class.java).also {
                    startActivity(it)
                }
            }

            R.id.option_logout -> {
                mainViewModel.logout()

            }
        }

        return super.onOptionsItemSelected(item)
    }

}