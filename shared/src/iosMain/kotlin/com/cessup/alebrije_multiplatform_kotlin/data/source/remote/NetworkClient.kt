package com.cessup.alebrije_multiplatform_kotlin.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class NetworkClient {
    actual val client = HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(NetworkExceptionInterceptor)
    }
}

actual fun isNoInternetException(throwable: Throwable): Boolean {
    val cause = throwable.cause
    // Simplified; you may inspect NSError code here
    return throwable.message?.contains("Network is unreachable", ignoreCase = true) == true
}