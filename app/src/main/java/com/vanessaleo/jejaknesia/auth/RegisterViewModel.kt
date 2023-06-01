package com.vanessaleo.jejaknesia.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanessaleo.jejaknesia.data.Event
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading
    val toastMessage: LiveData<Event<String>> = jejaknesiaRepo.toastMessage

    fun postRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            jejaknesiaRepo.postRegister(name, email, password)
        }
    }


}