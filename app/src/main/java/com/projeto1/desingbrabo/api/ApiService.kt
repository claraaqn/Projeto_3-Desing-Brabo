package com.projeto1.desingbrabo.api

import com.projeto1.desingbrabo.model.Cadastro
import com.projeto1.desingbrabo.model.LoginRequest
import com.projeto1.desingbrabo.model.LoginResponse
import com.projeto1.desingbrabo.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @POST("/register")
    fun registerUser(@Body cadastro: Cadastro): Call<ResponseBody>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("user/{id}")
    fun getUser(@Path("id") userId: Int): Call<User>
}
