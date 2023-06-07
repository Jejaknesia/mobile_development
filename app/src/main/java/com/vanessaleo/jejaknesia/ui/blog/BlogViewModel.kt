package com.vanessaleo.jejaknesia.ui.blog

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanessaleo.jejaknesia.api.ApiConfig
import com.vanessaleo.jejaknesia.data.Event
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.response.BlogResponse
import com.vanessaleo.jejaknesia.response.DataItem
import com.vanessaleo.jejaknesia.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading
//    val blogResponse: LiveData<BlogResponse> = jejaknesiaRepo.blogResponse
    val dataItem: LiveData<ArrayList<DataItem>> = jejaknesiaRepo.dataItem
    val snackbarText: LiveData<Event<String>> = jejaknesiaRepo.snackbarText

//   fun getDataBlog() : LiveData<ArrayList<DataItem>> {
////       viewModelScope.launch {
////           jejaknesiaRepo.getDataBlog()
////       }
//
//       return jejaknesiaRepo.getDataBlog()
//   }

    fun getBlogs(): LiveData<ArrayList<DataItem>> {
        return jejaknesiaRepo.getBlogs()
    }

}