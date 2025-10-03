package com.cessup.alebrije_multiplatform_kotlin.data.repositories
import com.cessup.alebrije_multiplatform_kotlin.data.source.AuthenticateRequest
import com.cessup.alebrije_multiplatform_kotlin.data.source.NoInternetException
import com.cessup.alebrije_multiplatform_kotlin.data.source.UserAPI
import com.cessup.alebrije_multiplatform_kotlin.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.tatarka.inject.annotations.Inject

/**
 * User Repository have every data about the users.
 *
 * This class is a interface with all functions about user information
 * There are actions that the user can perform
 *
 * @constructor Receiver a [UserAPI] an object got data from services
 *
 * @author
 *     Cessup
 * @since 1.0
 */
@Inject
class UserRepositoryImpl(private val api: UserAPI) : UserRepository {

    /**
     * This function insert a new user in the database
     *
     * @param email the user information from the services is here
     * @param password the user information from the services is here
     *
     * @return token the value is access to the system
     */
    override suspend fun authenticate(
        email: String,
        password: String
    ): Flow<Result<String>> =
        flow {
            try {
                val response = api.authenticate(AuthenticateRequest(email,password))
                println("Response: ${response}")
                emit(Result.success(response.token))
            } catch (e: NoInternetException) {
                // Handle no internet UI
                println("Response: ${e.message}")
                emit(Result.failure(e))
            } catch (e: Exception) {
                // Other exceptions
                println("Response: ${e.message}")
            }

        }
}