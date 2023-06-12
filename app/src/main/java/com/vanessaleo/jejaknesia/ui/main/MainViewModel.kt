package com.vanessaleo.jejaknesia.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanessaleo.jejaknesia.data.Event
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.model.DataModel
import com.vanessaleo.jejaknesia.model.UserModel
import com.vanessaleo.jejaknesia.response.DataResponse
import kotlinx.coroutines.launch

class MainViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading
    val dataResponse: LiveData<DataResponse> = jejaknesiaRepo.dataResponse
    val toastMessage: LiveData<Event<String>> = jejaknesiaRepo.toastMessage

    fun getUser() : LiveData<UserModel> {
        return jejaknesiaRepo.getUser()
    }

    fun logout() {
        viewModelScope.launch {
            jejaknesiaRepo.logout()
        }
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return jejaknesiaRepo.getThemeSetting()
    }

    fun saveThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            jejaknesiaRepo.saveThemeSetting(isDarkModeActive)
        }
    }

    fun postData(data: DataModel) {
        viewModelScope.launch {
            jejaknesiaRepo.postData(data)
        }
    }


}