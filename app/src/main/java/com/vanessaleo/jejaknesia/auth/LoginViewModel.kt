package com.vanessaleo.jejaknesia.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanessaleo.jejaknesia.data.Event
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.model.UserModel
import com.vanessaleo.jejaknesia.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading
    val loginResponse: LiveData<LoginResponse> = jejaknesiaRepo.loginResponse
    val toastMessage: LiveData<Event<String>> = jejaknesiaRepo.toastMessage

    fun postLogin(email: String, password: String) {
        viewModelScope.launch {
            jejaknesiaRepo.postLogin(email, password)
        }
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            jejaknesiaRepo.saveUser(user)
        }
    }

    fun login() {
        viewModelScope.launch {
            jejaknesiaRepo.login()
        }
    }

}