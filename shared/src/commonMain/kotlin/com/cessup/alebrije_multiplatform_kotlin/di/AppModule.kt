package com.cessup.alebrije_multiplatform_kotlin.di

import com.cessup.alebrije_multiplatform_kotlin.data.repositories.UserRepositoryImpl
import com.cessup.alebrije_multiplatform_kotlin.data.source.NetworkModule
import com.cessup.alebrije_multiplatform_kotlin.domain.repositories.UserRepository
import com.cessup.alebrije_multiplatform_kotlin.presentation.UserViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides


@Component
abstract class AppModule : NetworkModule() {
    abstract val userViewModel: UserViewModel

    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}