package com.cessup.alebrije_multiplatform_kotlin.data.source

import io.ktor.client.plugins.api.*

val NetworkExceptionInterceptor = createClientPlugin("NetworkExceptionInterceptor") {
    on(Send) { request ->
        try {
            proceed(request)
        } catch (cause: Throwable) {
            if (isNoInternetException(cause)) {
                throw NoInternetException("No internet connection", cause)
            } else {
                throw cause
            }
        }
    }
}

class NoInternetException(message: String, cause: Throwable?) : Exception(message, cause)

expect fun isNoInternetException(throwable: Throwable): Boolean


