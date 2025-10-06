package com.cessup.alebrije_multiplatform_kotlin.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

actual class NetworkClient {
    actual val client = HttpClient(CIO) {
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
    return throwable is UnknownHostException ||
            throwable is ConnectException ||
            throwable is SocketTimeoutException
}