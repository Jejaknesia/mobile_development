package com.vanessaleo.jejaknesia

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.play.integrity.internal.c
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vanessaleo.jejaknesia.auth.LoginActivity
import com.vanessaleo.jejaknesia.databinding.ActivityMainBinding
import com.vanessaleo.jejaknesia.ui.BlogActivity
import com.vanessaleo.jejaknesia.ui.PreferenceActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.home_page)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth
        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
            return
        }

        binding.apply {

            welcomeName.text = getString(R.string.welcome_name, firebaseUser.email)

            choosePref.setOnClickListener {
                val destination = editTextDestination.text.toString()
                val departureDate = editTextDepartureDate.text.toString()
                val arrivalDate = editTextArrivalDate.text.toString()

                if(destination.isNotEmpty() && departureDate.isNotEmpty() && arrivalDate.isNotEmpty()) {

                    val intent = Intent(this@MainActivity, PreferenceActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@MainActivity, "Pastikan semua field terisi terlebih dahulu", Toast.LENGTH_SHORT).show()

                }
            }

            editTextDepartureDate.setOnClickListener {
                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
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

                val datePickerDialog = DatePickerDialog(
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
        when(item.itemId) {
            R.id.option_blog -> {
                val blogPageIntent = Intent(this@MainActivity, BlogActivity::class.java)
                startActivity(blogPageIntent)
            }

            R.id.option_bookmark -> {
                // disini tertulis my saved trips
            }

            R.id.option_logout -> {
                firebaseAuth.signOut()

                val Intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(Intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}