package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class FileExtractionRequest(
    @SerializedName("essentials")
    val essentials: Essentials,
    @SerializedName("task")
    val task: String
) {
    data class Essentials(
        @SerializedName("files")
        val files: List<String?>
    )
}