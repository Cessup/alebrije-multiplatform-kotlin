package com.cessup.alebrije_multiplatform_kotlin.data.source

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class NetworkClient {
    actual val client = HttpClient(OkHttp) {
        install(NetworkExceptionInterceptor)
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000  // 15 seconds
            connectTimeoutMillis = 5_000
            socketTimeoutMillis = 5_000
        }
        expectSuccess = false

        engine {
            config {
                connectTimeout(5_000, java.util.concurrent.TimeUnit.MILLISECONDS)
                readTimeout(5_000, java.util.concurrent.TimeUnit.MILLISECONDS)
                writeTimeout(5_000, java.util.concurrent.TimeUnit.MILLISECONDS)
            }
        }
    }
}

actual fun isNoInternetException(throwable: Throwable): Boolean {
    return when (throwable) {
        is java.net.UnknownHostException,
        is java.net.ConnectException,
        is java.net.SocketTimeoutException,
        is io.ktor.network.sockets.SocketTimeoutException,
        is io.ktor.utils.io.errors.IOException -> true
        else -> false
    }
}