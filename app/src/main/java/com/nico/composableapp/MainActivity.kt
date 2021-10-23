package com.nico.composableapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nico.composableapp.ui.theme.ComposableAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen()
                }
            }
        }
    }
}

class UserViewModel: ViewModel() {

    fun login(username: String, password: String) {
        Log.d("DebugApp", "Username $username - Password: $password")
    }

}


@Composable
fun LoginScreen(userViewModel: UserViewModel = viewModel()) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginForm(
        username = username,
        password = password,
        onUsernameChange = { username = it },
        onPasswordChange = { password = it }
    ) { username, password ->
        userViewModel.login(username, password)
    }

}

@Composable
fun LoginForm(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClick: (username: String, password: String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(20.dp
            )
    ) {

        Text(text = "Login Screens")

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = onUsernameChange,
                placeholder = {
                    Text(text = "Username")
                })

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = onPasswordChange,
                placeholder = {
                    Text(text = "Password")
                })


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClick(username, password) }
        ) {
            Text("Log In")
        }
    }
}