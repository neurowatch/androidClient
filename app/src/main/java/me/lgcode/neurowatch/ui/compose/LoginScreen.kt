package me.lgcode.neurowatch.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.lgcode.neurowatch.R

@Composable
fun LoginScreen() {
    
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    Scaffold(
        
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Column {
                TextField(
                    placeholder = {
                        Text(text = stringResource(id = R.string.username))
                    },
                    value = username, 
                    onValueChange = {}
                )
                TextField(
                    placeholder = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    value = password,
                    onValueChange = {}
                )
                Button(
                    onClick = {
                        
                    }
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            }
        }
    }
}