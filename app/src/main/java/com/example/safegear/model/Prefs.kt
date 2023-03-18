package com.example.safegear.model

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_NAME = "com.cursokotlin.sharedpreferences"
    val PREFS_LASTNAME = "incial"
    val PREFS_IDENTIFICATION = "1061123456"
    val PREFS_JWT = "TokenJwt"
    val PREFS_ID = "123"
    val SHARED_NAME = "shared_name"
    val SHARED_LASTNAME = "shared_lastname"
    val SHARED_IDENTIFICATION = "shared_identification"
    val SHARED_ID = "shared_name"
    val SHARED_JWT = "shared_name"
    val prefsName: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    val prefsLastName: SharedPreferences = context.getSharedPreferences(PREFS_LASTNAME, 0)
    val prefsIdentification: SharedPreferences = context.getSharedPreferences(PREFS_IDENTIFICATION, 0)
    val prefsJwt: SharedPreferences = context.getSharedPreferences(PREFS_JWT, 0)

    val prefsIdUser: SharedPreferences = context.getSharedPreferences(PREFS_ID, 0)

    var id: String
        get() = prefsIdUser.getString(SHARED_ID, "").toString()
        set(value) = prefsIdUser.edit().putString(SHARED_ID, value).apply()

    var name: String
        get() = prefsName.getString(SHARED_NAME, "").toString()
        set(value) = prefsName.edit().putString(SHARED_NAME, value).apply()

    var lastname: String
        get() = prefsLastName.getString(SHARED_LASTNAME, "").toString()
        set(value) = prefsLastName.edit().putString(SHARED_LASTNAME, value).apply()

    var identification: String
        get() = prefsIdentification.getString(SHARED_IDENTIFICATION, "").toString()
        set(value) = prefsIdentification.edit().putString(SHARED_IDENTIFICATION, value).apply()

    var jwt: String
        get() = prefsJwt.getString(SHARED_JWT, "").toString()
        set(value) = prefsJwt.edit().putString(SHARED_JWT, value).apply()

}
