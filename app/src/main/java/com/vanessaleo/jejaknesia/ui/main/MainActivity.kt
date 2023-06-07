package com.vanessaleo.jejaknesia.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.auth.LoginActivity
import com.vanessaleo.jejaknesia.databinding.ActivityMainBinding
import com.vanessaleo.jejaknesia.ui.blog.BlogActivity
import com.vanessaleo.jejaknesia.ui.PreferenceActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.home_page)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupAdapter()
        setupViewModel()
        setupAction()

    }

//    private fun setupAdapter() {
//
//    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        showLoading()

        mainViewModel.getUser().observe(this@MainActivity) { user ->
            if (user.isLogin) {
                binding.welcomeName.text = getString(R.string.welcome_name, user.name)
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
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

    private fun setupAction() {
        binding.apply {


            choosePref.setOnClickListener {
                val destination = editTextDestination.text.toString().trim()
                val departureDate = editTextDepartureDate.text.toString().trim()
                val arrivalDate = editTextArrivalDate.text.toString().trim()


                if (destination.isNotEmpty() && departureDate.isNotEmpty() && arrivalDate.isNotEmpty()) {

                    val intent = Intent(this@MainActivity, PreferenceActivity::class.java)
                    startActivity(intent)

                } else {
                    if (destination.isEmpty()) {
                        editTextDestination.error = FIELD_REQUIRED

                    }

                    if (departureDate.isEmpty()) {
                        editTextDepartureDate.error = FIELD_REQUIRED
                    }

                    if (arrivalDate.isEmpty()) {
                        editTextArrivalDate.error = FIELD_REQUIRED
                    }

                }
            }

            editTextDepartureDate.setOnClickListener {
                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                @Suppress("NAME_SHADOWING") val datePickerDialog = DatePickerDialog(
                    this@MainActivity, { _, year, month, day ->
                        val date = (day.toString() + "-" + (month + 1) + "-" + year)
                        editTextDepartureDate.setText(date)

                    },
                    year,
                    month,
                    day
                )

                datePickerDialog.show()

            }


            editTextArrivalDate.setOnClickListener {
                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                @Suppress("NAME_SHADOWING") val datePickerDialog = DatePickerDialog(
                    this@MainActivity, { _, year, month, day ->
                        val date = (day.toString() + "-" + (month + 1) + "-" + year)
                        editTextArrivalDate.setText(date)

                    },
                    year,
                    month,
                    day
                )

                datePickerDialog.show()

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

            R.id.option_bookmark -> {
                // disini tertulis my saved trips
            }

            R.id.option_logout -> {
                mainViewModel.logout()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"

    }
}