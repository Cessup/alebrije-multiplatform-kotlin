package com.cessup.alebrije_multiplatform_kotlin.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel  constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Normal)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

     fun signInAction(email:String, keyword: String) {
         _uiState.value = UiState.Loading
         viewModelScope.launch {

         }
    }
}