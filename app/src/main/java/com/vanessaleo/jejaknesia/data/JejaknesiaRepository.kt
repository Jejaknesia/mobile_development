package com.vanessaleo.jejaknesia.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vanessaleo.jejaknesia.api.ApiService
import com.vanessaleo.jejaknesia.api.SecondApiService
import com.vanessaleo.jejaknesia.datastore.SettingPreference
import com.vanessaleo.jejaknesia.datastore.UserPreference
import com.vanessaleo.jejaknesia.model.DataModel
import com.vanessaleo.jejaknesia.model.UserModel
import com.vanessaleo.jejaknesia.response.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JejaknesiaRepository private constructor(
    private val userPref: UserPreference,
    private val settingPref: SettingPreference,
    private val apiService: ApiService,
    private val secondApiService: SecondApiService
) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    private val _dataItem = MutableLiveData<ArrayList<DataItem>>()
    private val dataItem: LiveData<ArrayList<DataItem>> = _dataItem

    private val _dataResponse = MutableLiveData<DataResponse>()
    val dataResponse: LiveData<DataResponse> = _dataResponse


    fun postRegister(name: String, email: String, password: String) {
        _isLoading.value = true

        val client = apiService.postRegister(name, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>,
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        _registerResponse.value = response.body()
                        _toastMessage.value = Event(response.body()?.status.toString())
                    }
                } else {
                    _toastMessage.value = Event(response.message().toString())
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                _toastMessage.value = Event(t.message.toString())

                Log.d(TAG, "onFailure ${t.message}")
            }

        })
    }

    fun postLogin(email: String, password: String) {
        _isLoading.value = true

        val client = apiService.postLogin(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _loginResponse.value = response.body()
                        _toastMessage.value = Event(response.body()?.message.toString())
                    }
                } else {
                    _toastMessage.value = Event("Invalid email or password")
                    Log.d(TAG, "onFailure: Invalid email or password")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _toastMessage.value = Event(t.message.toString())
                Log.d(TAG, "onFailure ${t.message.toString()}")
            }

        })
    }


    fun getBlogs(): LiveData<ArrayList<DataItem>> {
        _isLoading.value = true

        val client = apiService.getBlogs()
        client.enqueue(object : Callback<BlogResponse> {
            override fun onResponse(
                call: Call<BlogResponse>,
                response: Response<BlogResponse>,
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataItem.postValue(responseBody.data)
                        Log.d(TAG, "data : $responseBody.data")
                        _toastMessage.value = Event(responseBody.data.toString())
                    }
                }
            }

            override fun onFailure(call: Call<BlogResponse>, t: Throwable) {
                _isLoading.value = false
                _toastMessage.value = Event(t.message.toString())
                Log.d(TAG, "onFailure ${t.message.toString()}")
            }

        })

        return dataItem
    }


    fun postData(data: DataModel) {
        _isLoading.value = true

        val client = secondApiService.postData(data)
        client.enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                       _dataResponse.value = response.body()
                        _toastMessage.value = Event(response.body().toString())
                        Log.d(TAG, "onSuccess : ${response.body()}")

                    }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                _isLoading.value = false
                _toastMessage.value = Event(t.message.toString())
                Log.d(TAG, "onFailure ${t.message.toString()}")
            }

        })
    }


    fun getUser(): LiveData<UserModel> {
        return userPref.getUser().asLiveData()
    }

    suspend fun saveUser(userModel: UserModel) {
        userPref.saveUser(userModel)
    }

    suspend fun logout() {
        userPref.logout()
    }

   fun getThemeSetting(): LiveData<Boolean> {
       return settingPref.getThemeSetting().asLiveData()
   }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean){
        settingPref.saveThemeSetting(isDarkModeActive)
    }




    companion object {
        private const val TAG = "JejaknesiaRepository"

        @Volatile
        private var instance: JejaknesiaRepository? = null
        fun getInstance(
            userPref: UserPreference,
            settingPref: SettingPreference,
            apiService: ApiService,
            secondApiService: SecondApiService
        ): JejaknesiaRepository =
            instance ?: synchronized(this) {
                instance ?: JejaknesiaRepository(userPref, settingPref, apiService, secondApiService)
            }.also { instance = it }
    }
}