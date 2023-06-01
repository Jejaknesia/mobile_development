package com.vanessaleo.jejaknesia.auth

import android.app.Activity
import android.content.Intent
import android.net.http.HttpResponseCache
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.ViewModelFactory
import com.vanessaleo.jejaknesia.ui.main.MainActivity
import com.vanessaleo.jejaknesia.databinding.ActivityLoginBinding
import retrofit2.http.HTTP


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
                    binding.emailEditTextLayout.error = FIELD_REQUIRED
                }

                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = FIELD_REQUIRED
                }

                else -> {
                    showLoading()
                    setupLoginAuth()



                    loginViewModel.apply {
                        loginResponse.observe(this@LoginActivity) { loginResponse ->
                            if(loginResponse.status == "login success") {
                                AlertDialog.Builder(this@LoginActivity).apply {
                                    setTitle("Yeah")
                                    setMessage("Kamu berhasil masuk.")
                                    setPositiveButton(getString(R.string.confirm_message)) { _, _ ->
                                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
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



//    private fun updateUI(currentUser: FirebaseUser?) {
//        if (currentUser != null){
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        val currentUser = firebaseAuth.currentUser
//        updateUI(currentUser)
//    }

    companion object {
        private const val TAG = "LoginActivity"
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
    }
}

