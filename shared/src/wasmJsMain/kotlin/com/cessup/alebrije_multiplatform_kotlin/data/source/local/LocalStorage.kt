package com.cessup.alebrije_multiplatform_kotlin.data.source.local


import kotlinx.browser.localStorage

actual class LocalStorage actual constructor() {

    actual fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    actual fun getString(key: String): String? {
        return localStorage.getItem(key)
    }

    actual fun remove(key: String) {
        localStorage.removeItem(key)
    }

    actual fun clear() {
        localStorage.clear()
    }
}