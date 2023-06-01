package com.vanessaleo.jejaknesia.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository

class MainViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading


}