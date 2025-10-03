package com.cessup.alebrije_multiplatform_kotlin.data.source

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


actual class NetworkClient {
    actual val client = HttpClient(Js) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(NetworkExceptionInterceptor)
    }
}

actual fun isNoInternetException(throwable: Throwable): Boolean {
    // 1. TypeError is usually thrown when the network request fails
    // 2. You can also inspect the message for fetch-related failures
    return throwable::class.simpleName == "TypeError" ||
            throwable.message?.contains("NetworkError", ignoreCase = true) == true ||
            throwable.message?.contains("Failed to fetch", ignoreCase = true) == true
}