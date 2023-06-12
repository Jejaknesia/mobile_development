package com.vanessaleo.jejaknesia.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.vanessaleo.jejaknesia.api.ApiConfig
import com.vanessaleo.jejaknesia.api.SecondApiConfig
import com.vanessaleo.jejaknesia.data.JejaknesiaRepository
import com.vanessaleo.jejaknesia.datastore.SettingPreference
import com.vanessaleo.jejaknesia.datastore.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

object Injection {
    fun provideRepository(context: Context) : JejaknesiaRepository {
        val userPreference = UserPreference.getInstance(context.dataStore)
        val settingPreference = SettingPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val secondApiService = SecondApiConfig.getApiService()
        return JejaknesiaRepository.getInstance(userPreference, settingPreference, apiService, secondApiService)
    }

}