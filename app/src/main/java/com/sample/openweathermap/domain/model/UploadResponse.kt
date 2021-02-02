package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("file")
    val file: File
) {
    data class File(
        @SerializedName("directURL")
        val directURL: String,
        @SerializedName("filetype")
        val filetype: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("protected")
        val `protected`: Boolean,
        @SerializedName("size")
        val size: String
    )
}