package com.cessup.alebrije_multiplatform_kotlin.ui.session


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel constructor(
) : ViewModel() {

    suspend fun signUp(email:String, passowrd: String) {

    }
}