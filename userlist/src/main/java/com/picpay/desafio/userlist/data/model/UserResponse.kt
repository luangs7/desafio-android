package com.picpay.desafio.userlist.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?
)