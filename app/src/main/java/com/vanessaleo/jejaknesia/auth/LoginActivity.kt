package com.vanessaleo.jejaknesia.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.ui.main.MainActivity
import com.vanessaleo.jejaknesia.databinding.ActivityLoginBinding
import com.vanessaleo.jejaknesia.model.UserModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupViewModel()
        setupAction()

    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = resources.getString(R.string.input_email)
                }

                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = resources.getString(R.string.input_password)
                }

                else -> {
                    showLoading()
                    setupLoginAuth()

                    loginViewModel.toastMessage.observe(this@LoginActivity) {
                        it.getContentIfNotHandled()?.let { msg ->
                            Toast.makeText(
                                this@LoginActivity, msg, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    loginViewModel.apply {
                        loginResponse.observe(this@LoginActivity) { loginResponse ->
                            if(!loginResponse.error) {
                                AlertDialog.Builder(this@LoginActivity).apply {
                                    setTitle("Yeah")
                                    setMessage("Kamu berhasil masuk.")
                                    setPositiveButton(getString(R.string.confirm_message)) { _, _ ->
                                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        startActivity(intent)
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

        }

        binding.tvRegisterHere.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun setupLoginAuth() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        loginViewModel.postLogin(email, password)

        loginViewModel.apply {
            loginResponse.observe(this@LoginActivity) { loginResp ->
                saveUser(
                    UserModel(
                        loginResp.loginResult.name,
                        loginResp.loginResult.token,
                        true
                    )
                )
            }
        }



    }

    private fun showLoading() {
        loginViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        }
    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)
    }
}

