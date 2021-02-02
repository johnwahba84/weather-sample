package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class ImageQualityRequest(
    @SerializedName("essentials")
    val essentials: Essentials
) {
    data class Essentials(
        @SerializedName("acceptanceThreshold")
        val acceptanceThreshold: String,
        @SerializedName("files")
        val files: List<String?>,
        @SerializedName("qualityParameter")
        val qualityParameter: String
    )
}