package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class AuthenticateRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)