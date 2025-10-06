package com.cessup.alebrije_multiplatform_kotlin.data.source.local


actual class LocalStorage actual constructor() {
    private val map = mutableMapOf<String, String>()

    actual fun putString(key: String, value: String) {
        map[key] = value
    }

    actual fun getString(key: String): String? {
        return map[key]
    }

    actual fun remove(key: String) {
        map.remove(key)
    }

    actual fun clear() {
        map.clear()
    }
}