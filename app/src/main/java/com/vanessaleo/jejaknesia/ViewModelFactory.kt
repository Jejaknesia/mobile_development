package com.vanessaleo.jejaknesia

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vanessaleo.jejaknesia.auth.LoginViewModel
import com.vanessaleo.jejaknesia.auth.RegisterViewModel
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.di.Injection
import com.vanessaleo.jejaknesia.ui.main.MainViewModel

class ViewModelFactory(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(jejaknesiaRepo) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(jejaknesiaRepo) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(jejaknesiaRepo) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}