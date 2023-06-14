package com.vanessaleo.jejaknesia.api

import com.vanessaleo.jejaknesia.model.DataModel
import com.vanessaleo.jejaknesia.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<LoginResponse>

    @GET("api/blogs")
    fun getBlogs() : Call<BlogResponse>

    @POST("api/predict")
    @Headers("Content-Type: application/json")
    fun postData(@Body data: DataModel): Call<CategoryResponse>
}