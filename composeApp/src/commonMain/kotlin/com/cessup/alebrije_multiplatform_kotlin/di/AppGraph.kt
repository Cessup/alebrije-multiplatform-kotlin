package com.cessup.alebrije_multiplatform_kotlin.di

object AppGraph {
    val component by lazy { AppComponent::class.create() }
}