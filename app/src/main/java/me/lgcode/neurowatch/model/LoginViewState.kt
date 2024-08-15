package me.lgcode.neurowatch.model

sealed class LoginViewState {
    data object Loading : LoginViewState()
    data object Error : LoginViewState()
    data object Success : LoginViewState()
    data object TokenExists : LoginViewState()
    data object LoginRequired : LoginViewState()
}