package com.cessup.alebrije_multiplatform_kotlin.domain.repositories

import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun authenticate(email: String, password:String) : Flow<Result<String>>
}