package com.example.safegear.model

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val PREFS_NAME = "com.cursokotlin.sharedpreferences"
    val PREFS_ID = "123"
    val SHARED_NAME = "shared_name"
    val SHARED_LASTNAME = "shared_lastname"
    val SHARED_IDENTIFICATION = "shared_identification"
    val SHARED_ID = "shared_name"
    val prefsName: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    val prefsIdUser: SharedPreferences = context.getSharedPreferences(PREFS_ID, 0)

    var id: String
        get() = prefsIdUser.getString(SHARED_ID, "").toString()
        set(value) = prefsIdUser.edit().putString(SHARED_ID, value).apply()

    var name: String
        get() = prefsName.getString(SHARED_NAME, "").toString()
        set(value) = prefsName.edit().putString(SHARED_NAME, value).apply()

    var lastname: String
        get() = prefsName.getString(SHARED_LASTNAME, "").toString()
        set(value) = prefsName.edit().putString(SHARED_NAME, value).apply()

    var identification: String
        get() = prefsName.getString(SHARED_IDENTIFICATION, "").toString()
        set(value) = prefsName.edit().putString(SHARED_NAME, value).apply()
}
