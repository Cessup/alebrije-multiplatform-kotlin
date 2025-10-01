package com.cessup.alebrije_multiplatform_kotlin.data.repositories
import com.cessup.alebrije_multiplatform_kotlin.data.source.UserAPI
import com.cessup.alebrije_multiplatform_kotlin.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl(private val api: UserAPI) : UserRepository {

    /**
     * This function insert a new user in the database
     *
     * @param email the user information from the services is here
     * @param password the user information from the services is here
     *
     * @return token the value is an access to the system
     */
    override suspend fun authenticate(
        email: String,
        password: String
    ): Flow<Result<String>> =
        flow {
            try {
                val response = api.authenticate(email,password)
                emit(Result.success(response))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
}