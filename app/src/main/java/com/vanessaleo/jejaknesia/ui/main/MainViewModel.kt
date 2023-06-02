package com.vanessaleo.jejaknesia.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.model.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading

    fun getUser() : LiveData<UserModel> {
        return jejaknesiaRepo.getUser()
    }

    fun logout() {
        viewModelScope.launch {
            jejaknesiaRepo.logout()
        }
    }

}