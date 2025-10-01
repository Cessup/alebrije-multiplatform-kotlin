package com.cessup.alebrije_multiplatform_kotlin.data.entities


/**
 * Represents a user details with the structure of the database.
 *
 * @property id unique identifier
 * @property name the name of the user
 * @property lastName the lastname of the user
 * @property gender the gender of the user
 * @property birthdate the birthday of the user
 */
data class UserDetailsEntity(
    val id: String,
    val name: String,
    val lastName: String,
    val address: String,
    val gender: String,
    val birthdate : Long
)