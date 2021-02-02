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
            const val API_ID = ""
            const val API_PASSWORD = ""
        }
    }

    @StringDef(ApiPath.AUTHENTICATE, ApiPath.UPLOAD)
    annotation class ApiPath {
        companion object {
            const val AUTHENTICATE = "login"
            const val UPLOAD = "upload"
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
