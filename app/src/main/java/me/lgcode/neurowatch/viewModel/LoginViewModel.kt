package me.lgcode.neurowatch.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.lgcode.neurowatch.model.LoginRequest
import me.lgcode.neurowatch.repo.NeurowatchRepo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repo: NeurowatchRepo): ViewModel() {
    
    fun performLogin(username: String, password: String) {
        viewModelScope.launch { 
            repo.login(LoginRequest(username, password)).fold(
                onSuccess = {
                    
                },
                onFailure = {
                    
                }
            )
        }
    }
    
}