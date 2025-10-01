package com.cessup.alebrije_multiplatform_kotlin

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import me.tatarka.inject.annotations.Inject

@Inject
class ApiService {
    private val client = NetworkClient().client

    suspend fun getExample(): String {
        val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/posts/1")
        return response.body()
    }
}