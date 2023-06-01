package com.vanessaleo.jejaknesia.auth

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.vanessaleo.jejaknesia.R
import com.vanessaleo.jejaknesia.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val retypePassword = binding.editTextRetypePassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && retypePassword.isNotEmpty()) {
                if(password == retypePassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { result ->
                        if(result.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Pendaftaran akun berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@RegisterActivity, "Periksa kembali field yang sudah diisi", Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    Toast.makeText(this@RegisterActivity, "Password yang dimasukkan tidak sesuai", Toast.LENGTH_SHORT).show()

                }
            } else {
                if(email.isEmpty()) {
                    binding.editTextEmail.error = FIELD_REQUIRED
                }

                if(!isValidEmail(email)) {
                    binding.editTextEmail.error = FIELD_IS_NOT_VALID
                }

                if(password.isEmpty()) {
                    binding.editTextPassword.error = FIELD_REQUIRED
                }

                if(retypePassword.isEmpty()){
                    binding.editTextRetypePassword.error = FIELD_REQUIRED
                }

            }
        }

        binding.tvLoginHere.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"

    }


}