package com.cessup.alebrije_multiplatform_kotlin.presentation

import com.cessup.alebrije_multiplatform_kotlin.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import kotlinx.coroutines.MainScope

@Inject
class SignInViewModel(private val userRepository: UserRepository){
    private val _uiState = MutableStateFlow<UiState>(UiState.Normal)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val scope = MainScope()
    fun signInAction(email:String, keyword: String) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val authResult: Flow<Result<String>> = userRepository.authenticate(email,keyword)
            authResult.collect { result ->
                result.onSuccess { message ->
                    _uiState.value = UiState.Success(result.getOrNull().orEmpty())
                }.onFailure { exception ->
                //val errorMessage = handleNetworkError(exception as Exception)
                _uiState.value = UiState.NetworkError(exception.message.toString())
                }
            }
        }
    }

    // Manual bridge for Swift
    fun startObservingUiState(onUpdate: (UiState) -> Unit): Job {
        return viewModelScope.launch {
            uiState.collect {
                onUpdate(it)
            }
        }
    }

    fun stopObserving(job: Job) {
        job.cancel()
    }
}