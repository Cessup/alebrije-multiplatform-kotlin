package com.cessup.alebrije_multiplatform_kotlin.data.source.local


expect class LocalStorage() {
    fun putString(key: String, value: String)
    fun getString(key: String): String?
    fun remove(key: String)
    fun clear()
}