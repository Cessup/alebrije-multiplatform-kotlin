package com.cessup.alebrije_multiplatform_kotlin.data.source.local

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.MainScope

actual class LocalStorage actual constructor() {

    private val prefs: SharedPreferences = SharedPrefsProvider.get()

    actual fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    actual fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    actual fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    actual fun clear() {
        prefs.edit().clear().apply()
    }
}

object SharedPrefsProvider {
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    fun get(): SharedPreferences = prefs
}
