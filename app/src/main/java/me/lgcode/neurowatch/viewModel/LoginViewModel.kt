package me.lgcode.neurowatch.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.model.LoginViewState
import me.lgcode.neurowatch.repo.NeurowatchRepo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repo: NeurowatchRepo): ViewModel() {
    
    private var _viewState: MutableStateFlow<LoginViewState?> = MutableStateFlow(null)
    val viewState = _viewState.asStateFlow()
    
    fun performLogin(username: String, password: String) {
        _viewState.value = LoginViewState.Loading
        viewModelScope.launch { 
            repo.login(LoginRequest(username, password)).fold(
                onSuccess = {
                    _viewState.value = LoginViewState.Success
                },
                onFailure = {
                    _viewState.value = LoginViewState.Error
                }
            )
        }
    }
    
    fun checkLoginStatus() {
        viewModelScope.launch {
            repo.getToken().fold(
                onSuccess = {
                    _viewState.value = LoginViewState.TokenExists
                },
                onFailure = {
                    _viewState.value = LoginViewState.LoginRequired
                }
            )
        }
    }
}