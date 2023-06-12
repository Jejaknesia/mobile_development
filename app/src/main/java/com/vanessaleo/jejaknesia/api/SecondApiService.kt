package com.vanessaleo.jejaknesia.api


import com.vanessaleo.jejaknesia.model.DataModel
import com.vanessaleo.jejaknesia.response.DataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SecondApiService {
    @POST("predict_place")
    @Headers("Content-Type: application/json")
//    fun postRawData(@Body data: RequestBody): Call<ResponseBody>
    fun postData(@Body data: DataModel): Call<DataResponse>


}