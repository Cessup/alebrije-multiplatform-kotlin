package com.cessup.alebrije_multiplatform_kotlin

import io.ktor.client.*

expect class KtorHttpClient() {
    val client: HttpClient
}