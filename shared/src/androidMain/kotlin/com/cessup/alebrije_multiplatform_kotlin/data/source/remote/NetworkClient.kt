package com.cessup.alebrije_multiplatform_kotlin.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

actual class NetworkClient {
    actual val client = HttpClient(OkHttp) {
        install(NetworkExceptionInterceptor)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000  // 15 seconds
            connectTimeoutMillis = 5_000
            socketTimeoutMillis = 5_000
        }
        expectSuccess = false

        install(NetworkExceptionInterceptor)

        engine {
            config {
                connectTimeout(5_000, TimeUnit.MILLISECONDS)
                readTimeout(5_000, TimeUnit.MILLISECONDS)
                writeTimeout(5_000, TimeUnit.MILLISECONDS)
            }
        }
    }
}

actual fun isNoInternetException(throwable: Throwable): Boolean {
    return when (throwable) {
        is UnknownHostException,
        is ConnectException,
        is SocketTimeoutException,
        is io.ktor.network.sockets.SocketTimeoutException,
        is IOException -> true
        else -> false
    }
}