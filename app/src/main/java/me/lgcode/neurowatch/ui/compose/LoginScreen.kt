package me.lgcode.neurowatch.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.lgcode.neurowatch.R
import me.lgcode.neurowatch.model.LoginViewState
import me.lgcode.neurowatch.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state = viewModel.viewState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.checkLoginStatus()
    }
    
    when (state.value) {
        LoginViewState.Success,
        LoginViewState.TokenExists -> {
            navController.navigate("video_list")
        }
        LoginViewState.Error -> {
            // TODO: Show an error
        }
        LoginViewState.Loading -> {
            // TODO: Show loading state
        }
        else -> {
            // Login Required
        }
    }
    
    Scaffold {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Column {
                TextField(
                    placeholder = {
                        Text(text = stringResource(id = R.string.username))
                    },
                    value = username, 
                    onValueChange = {
                        username = it
                    }
                )
                TextField(
                    placeholder = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )
                Button(
                    onClick = {
                        viewModel.performLogin(username, password)
                    }
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            }
        }
    }
}