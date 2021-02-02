package com.sample.openweathermap.constants

import androidx.annotation.IntDef
import androidx.annotation.StringDef

object AppConstants {

    @StringDef(
        ApiConfiguration.URL,
        ApiConfiguration.URL_UPLOAD,
        ApiConfiguration.API_ID,
        ApiConfiguration.API_PASSWORD
    )
    annotation class ApiConfiguration {
        companion object {
            const val URL = "https://preproduction.signzy.tech/api/v2/patrons/"
            const val URL_UPLOAD = "https://preproduction-persist.signzy.tech/api/files/"
            const val API_ID = "adq_test"
            const val API_PASSWORD = "Wi4jggLmvST86YXuCLyM"
        }
    }

    @StringDef(ApiPath.AUTHENTICATE, ApiPath.UPLOAD, ApiPath.IMAGE_QUALITY, ApiPath.FILE_EXTRACTION)
    annotation class ApiPath {
        companion object {
            const val AUTHENTICATE = "login"
            const val UPLOAD = "upload"
            const val IMAGE_QUALITY = "{userID}/imagequality"
            const val FILE_EXTRACTION = "{userID}/foreignidentitiesextractions"
        }
    }

    @IntDef(Permission.PERMISSION_ID)
    annotation class Permission {
        companion object {
            const val PERMISSION_ID = 44
        }
    }

    @StringDef(Database.NAME)
    annotation class Database {
        companion object {
            const val NAME = "SignZY"
        }
    }
}
