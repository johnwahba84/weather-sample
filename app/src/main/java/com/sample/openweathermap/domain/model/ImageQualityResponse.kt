package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class ImageQualityResponse(
    @SerializedName("essentials")
    val essentials: Essentials,
    @SerializedName("id")
    val id: String,
    @SerializedName("patronId")
    val patronId: String,
    @SerializedName("result")
    val result: Result
) {
    data class Essentials(
        @SerializedName("files")
        val files: List<String>
    )

    data class Result(
        @SerializedName("extractionQuality")
        val extractionQuality: String,
        @SerializedName("msg")
        val msg: String,
        @SerializedName("qualityScores")
        val qualityScores: QualityScores,
        @SerializedName("score")
        val score: String,
        @SerializedName("summary")
        val summary: String
    ) {
        data class QualityScores(
            @SerializedName("brightness")
            val brightness: Brightness,
            @SerializedName("compressionQuality")
            val compressionQuality: CompressionQuality,
            @SerializedName("sharpness")
            val sharpness: Sharpness,
            @SerializedName("textQuality")
            val textQuality: TextQuality
        ) {
            data class Brightness(
                @SerializedName("score")
                val score: String,
                @SerializedName("valid")
                val valid: String
            )

            data class CompressionQuality(
                @SerializedName("score")
                val score: String,
                @SerializedName("valid")
                val valid: String
            )

            data class Sharpness(
                @SerializedName("score")
                val score: String,
                @SerializedName("valid")
                val valid: String
            )

            data class TextQuality(
                @SerializedName("score")
                val score: String,
                @SerializedName("valid")
                val valid: String
            )
        }
    }
}