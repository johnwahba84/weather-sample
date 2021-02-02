package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName
import java.io.File

data class UploadRequest(
        @SerializedName("file")
        val file: File,
        @SerializedName("key")
        val key: String,
        @SerializedName("optimize")
        val optimize: String,
        @SerializedName("ttl")
        val ttl: String
    )