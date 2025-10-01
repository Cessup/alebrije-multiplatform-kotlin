package com.cessup.alebrije_multiplatform_kotlin

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*

class ApiService {
    private val client = KtorHttpClient().client

    suspend fun getExample(): String {
        val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/posts/1")
        return response.body()
    }

    suspend fun getPostTitle(): String {
        val response: String = client.get("https://jsonplaceholder.typicode.com/posts/1").body()
        return response
    }
}