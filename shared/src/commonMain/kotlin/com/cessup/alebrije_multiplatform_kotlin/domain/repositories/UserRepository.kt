package com.cessup.alebrije_multiplatform_kotlin.domain.repositories

import domain.models.User

interface UserRepository {
    suspend fun registerUser(user: User)
}