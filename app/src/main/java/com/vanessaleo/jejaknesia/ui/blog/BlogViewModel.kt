package com.vanessaleo.jejaknesia.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.response.DataItem

class BlogViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading

    fun getBlogs(): LiveData<ArrayList<DataItem>> {
        return jejaknesiaRepo.getBlogs()
    }

}