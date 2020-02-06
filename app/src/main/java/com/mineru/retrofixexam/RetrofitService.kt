package com.mineru.retrofixexam

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Body
import retrofit2.http.PUT
import java.util.HashMap

interface RetrofitService {
    @Headers("accept: application/json",
        "content-type: application/json")
    @POST("/api/v1/user/signup")
    fun signup(
        @Header("Authorization") token: String,
        @Body params: HashMap<String, Any>
    ): Call<UserData>

    @Headers("accept: application/json",
        "content-type: application/json")
    @POST("/api/v1/user/signup_g")
    fun signup_google(
        @Header("Authorization") token: String,
        @Body params: HashMap<String, Any>
    ): Call<UserData>

    @Headers("accept: application/json",
        "content-type: application/json")
    @POST("/api/v1/user/signin")
    fun signin(
        @Header("Authorization") token: String,
        @Body params: HashMap<String, Any>
    ): Call<UserData>

    @Headers("accept: application/json",
        "content-type: application/json")
    @PUT("/api/v1/user/reset")
    fun reset(
        @Header("Authorization") token: String,
        @Body params: HashMap<String, Any>
    ): Call<UserData>

    @Headers("accept: application/json",
        "content-type: application/json")
    @PUT("/api/v1/user/inactive ")
    fun inactive (
        @Header("Authorization") token: String,
        @Body params: HashMap<String, Any>
    ): Call<UserData>
}