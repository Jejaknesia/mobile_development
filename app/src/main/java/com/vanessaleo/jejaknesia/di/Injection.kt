package com.vanessaleo.jejaknesia.di

import android.content.Context
import com.vanessaleo.jejaknesia.api.ApiConfig
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository

object Injection {
    fun provideRepository(context: Context) : JejaknesiaRepository {
        val apiService = ApiConfig.getApiService()
        return JejaknesiaRepository.getInstance(apiService)
    }

}