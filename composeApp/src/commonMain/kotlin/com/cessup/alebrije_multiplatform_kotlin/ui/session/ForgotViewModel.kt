package com.cessup.alebrije_multiplatform_kotlin.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForgotViewModel constructor(
) : ViewModel() {

    private val _beers = MutableStateFlow<List<String>>(emptyList())
    val beers: StateFlow<List<String>> = _beers

    init {
        viewModelScope.launch {

        }
    }
}