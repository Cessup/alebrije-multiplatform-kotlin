package com.cessup.alebrije_multiplatform_kotlin.data.source

import io.ktor.client.*
import me.tatarka.inject.annotations.Provides


open class NetworkModule {
    private val networkClient = NetworkClient()

    @Provides
    fun provideHttpClient(): HttpClient = networkClient.client
}
//Get Ever Client from the modules
expect class NetworkClient() {
    val client: HttpClient
}