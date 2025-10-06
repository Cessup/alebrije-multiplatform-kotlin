package com.cessup.alebrije_multiplatform_kotlin.data.source.local

import platform.Foundation.NSUserDefaults

actual class LocalStorage actual constructor() {

    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun putString(key: String, value: String) {
        defaults.setObject(value, forKey = key)
    }

    actual fun getString(key: String): String? {
        return defaults.stringForKey(key)
    }

    actual fun remove(key: String) {
        defaults.removeObjectForKey(key)
    }

    actual fun clear() {
        val keys = defaults.dictionaryRepresentation().keys
        keys.forEach { key ->
            defaults.removeObjectForKey(key as String)
        }
    }
}