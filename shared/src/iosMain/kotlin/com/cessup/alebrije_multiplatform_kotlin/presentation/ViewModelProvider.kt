package com.cessup.alebrije_multiplatform_kotlin.presentation

import com.cessup.alebrije_multiplatform_kotlin.di.AppComponent
import com.cessup.alebrije_multiplatform_kotlin.di.create

object ViewModelProvider {
    val component by lazy { AppComponent::class.create() }

    fun provideSignInViewModel(): SignInViewModel {
        return component.signInViewModel
    }
}