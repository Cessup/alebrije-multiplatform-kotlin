package com.cessup.alebrije_multiplatform_kotlin.data.source

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.tatarka.inject.annotations.Inject


@Inject
class UserAPI(private val client: HttpClient) {

    val baseUrl = "http:/localhost:8080"

    suspend fun authenticate(request: AuthenticateRequest): TokenResponse {
        try {
            return client.post("$baseUrl/session/authenticate") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            println("Success")
        } catch (e: HttpRequestTimeoutException) {
            println("Timed out waiting for response: ${e.message}")
            throw e
        } catch (e: Throwable) {
            println("Other failure: ${e::class.simpleName} - ${e.message}")
            throw e
        }
    }
}


/**
 * Request to Register Details of a new user
 *
 * @property email All information about this user
 * @property password All information about this user
 */
data class AuthenticateRequest(val email: String, val password: String)

/**
 * Request to Register Details of a new user
 *
 * @property token unique identifier about session from server.
 */
data class TokenResponse(val token: String)