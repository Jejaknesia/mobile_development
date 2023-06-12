//package com.vanessaleo.jejaknesia.ui
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.gson.JsonArray
//import com.vanessaleo.jejaknesia.data.Event
//import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
//import com.vanessaleo.jejaknesia.response.LoginResponse
//import com.vanessaleo.jejaknesia.response.PreferenceResponse
//import kotlinx.coroutines.launch
//
//class PreferenceViewModel(private val jejaknesiaRepo: JejaknesiaRepository) : ViewModel() {
//    val isLoading: LiveData<Boolean> = jejaknesiaRepo.isLoading
//    val preferenceResponse: LiveData<PreferenceResponse> = jejaknesiaRepo.prefResponse
//    val toastMessage: LiveData<Event<String>> = jejaknesiaRepo.toastMessage
//
//    fun postPreference(jsonArray: JsonArray) {
//        viewModelScope.launch {
//            jejaknesiaRepo.postPreference(jsonArray)
//        }
//    }
//}