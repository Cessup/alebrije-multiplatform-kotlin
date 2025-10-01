package com.cessup.alebrije_multiplatform_kotlin

import io.ktor.client.*
import me.tatarka.inject.annotations.Component

@Component
abstract class KtorHttpClient

expect class NetworkClient(): KtorHttpClient {
    val client: HttpClient
}