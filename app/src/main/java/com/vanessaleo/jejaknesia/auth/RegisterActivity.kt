package com.vanessaleo.jejaknesia.auth

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val registerViewModel: RegisterViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        setupViewModel()
        setupAction()


    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

           when {
               name.isEmpty() -> {
                   binding.nameEditTextLayout.error = FIELD_REQUIRED
               }

               email.isEmpty() -> {
                   binding.emailEditTextLayout.error = FIELD_REQUIRED
               }

               !isValidEmail(email) -> {
                   binding.emailEditTextLayout.error = FIELD_IS_NOT_VALID
               }

               password.isEmpty() -> {
                   binding.passwordEditTextLayout.error = FIELD_REQUIRED
               }

               else -> {
                   showLoading()
                   setupRegisterAuth()

                   registerViewModel.toastMessage.observe(this@RegisterActivity) {
                       it.getContentIfNotHandled().let {
                           AlertDialog.Builder(this@RegisterActivity).apply {
                               setTitle("Yeah!")
                               setMessage(getString(R.string.alert_message))
                               setPositiveButton(getString(R.string.confirm_message)) { _, _ ->
                                   startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                   finish()
                               }

                               create()
                               show()
                           }
                       }
                   }


               }
           }


        }

        binding.tvLoginHere.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun setupRegisterAuth() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        registerViewModel.postRegister(name, email, password)
    }

    private fun showLoading() {
        registerViewModel.isLoading.observe(this) { isLoading ->
            if(isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {
        private const val FIELD_REQUIRED = "Field ini tidak boleh kosong"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"

    }


}