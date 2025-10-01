package com.cessup.alebrije_multiplatform_kotlin.data.repositories

import com.cessup.alebrije_multiplatform_kotlin.ApiService
import com.cessup.alebrije_multiplatform_kotlin.domain.repositories.UserRepository
import domain.models.User

class UserRepositoryImpl(private val api: ApiService) : UserRepository {
    override suspend fun registerUser(user: User) {

    }
}