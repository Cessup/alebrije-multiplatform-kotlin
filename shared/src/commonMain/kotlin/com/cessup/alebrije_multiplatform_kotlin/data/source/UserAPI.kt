package com.cessup.alebrije_multiplatform_kotlin.data.source

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import me.tatarka.inject.annotations.Inject


@Inject
class UserAPI(private val client: HttpClient) {

    val baseUrl = "http:/localhost:8080"

    suspend fun authenticate(email: String, password: String): String {
        val response: HttpResponse = client.get("$baseUrl/session/authenticate") {
            url {
                parameters.append("email", email)
                parameters.append("password", password)
            }
        }
        return response.body()
    }
}