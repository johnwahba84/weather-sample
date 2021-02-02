package com.sample.openweathermap.domain.model


import com.google.gson.annotations.SerializedName

data class FileExtractionResponse(
    @SerializedName("essentials")
    val essentials: Essentials,
    @SerializedName("id")
    val id: String,
    @SerializedName("patronId")
    val patronId: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("task")
    val task: String
) {
    data class Essentials(
        @SerializedName("files")
        val files: List<String>
    )

    data class Result(

        @SerializedName("cardNumber")
        val cardNumber: String,
        @SerializedName("dob")
        val dob: String,
        @SerializedName("expiryDate")
        val expiryDate: String,
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("idNumber")
        val idNumber: String,
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("nationality")
        val nationality: String,
        @SerializedName("nationalityCode")
        val nationalityCode: String,

        @SerializedName("surname")
        val surname: String,
        @SerializedName("passportNumber")
        val passportNumber: String,
        @SerializedName("countryCode")
        val countryCode: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("dateOfBirth")
        val dateOfBirth: String,
        @SerializedName("dateOfExpiry")
        val dateOfExpiry: String
    )
}