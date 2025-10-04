package com.cessup.alebrije_multiplatform_kotlin.di

import com.cessup.alebrije_multiplatform_kotlin.data.repositories.UserRepositoryImpl
import com.cessup.alebrije_multiplatform_kotlin.data.source.NetworkModule
import com.cessup.alebrije_multiplatform_kotlin.domain.repositories.UserRepository
import com.cessup.alebrije_multiplatform_kotlin.presentation.SignInViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides


@Component
abstract class AppComponent : NetworkModule() {
    abstract val signInViewModel: SignInViewModel

    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}