package com.cessup.alebrije_multiplatform_kotlin.presentation


interface UiState {
    object Normal : UiState
    object Loading : UiState
    data class NetworkError(val message: String) : UiState
    data class Success(val data: String) : UiState
    data class Error(val message: String) : UiState
}