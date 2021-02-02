package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class AuthenticateResponse(
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("ttl")
    val ttl: String,
    @SerializedName("userId")
    val userId: String
)