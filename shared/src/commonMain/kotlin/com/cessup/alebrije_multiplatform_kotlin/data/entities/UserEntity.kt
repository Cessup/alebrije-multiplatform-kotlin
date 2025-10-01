package com.cessup.alebrije_multiplatform_kotlin.data.entities


/**
 * Represents a user with the structure of the database.
 *
 * @property id unique identifier
 * @property email the email the user belongs to
 * @property phone the email the user belongs to
 * @property password the password of the user for account
 */
data class UserEntity(
    val id: String,
    val email: String,
    val phone: String,
    val nickname: String,
    val password: String,
    val details: UserDetailsEntity
)