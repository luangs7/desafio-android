package com.picpay.desafio.userlist.data.service

import com.picpay.desafio.userlist.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}