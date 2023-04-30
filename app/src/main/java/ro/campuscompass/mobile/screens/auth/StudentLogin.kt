package ro.campuscompass.mobile.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.screens.utils.AuthText
import ro.campuscompass.mobile.screens.utils.EmailTextField
import ro.campuscompass.mobile.screens.utils.PasswordTextField
import ro.campuscompass.mobile.screens.utils.isEmailValid
import ro.campuscompass.mobile.services.auth.EmailAndPasswordClient
import ro.campuscompass.mobile.services.auth.SignInResult
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme


@Composable
fun StudentLogin(
    emailAndPasswordClient: EmailAndPasswordClient,
    onLoginClick: (SignInResult) -> Unit,
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isButtonEnabled by remember {
        derivedStateOf {
            isEmailValid(email) && password.isNotEmpty()
        }
    }

    var loginInProgress by remember { mutableStateOf(false) }
    val loginCoroutineScope = rememberCoroutineScope()

    val logInError = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = logInError.value) {
        logInError.value?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    val onLoginButtonClick: () -> Unit = {
        loginInProgress = true
        loginCoroutineScope.launch {
            val signInResult = emailAndPasswordClient.login(
                email = email,
                password = password,
            )

            if (signInResult.error == null) {
                onLoginClick(signInResult)
                return@launch
            }
            logInError.value = signInResult.error
            loginInProgress = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthText(R.string.student_login)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailTextField(
                email = email,
                onEmailChange = { email = it },
            )
            PasswordTextField(
                password = password,
                onPasswordChange = { password = it },
            )
            if (loginInProgress) {
                CircularProgressIndicator()
            }
            Button(
                enabled = isButtonEnabled,
                onClick = onLoginButtonClick,
            ) {
                Text(stringResource(R.string.login))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StudentLoginPreview() {
    CampusCompassMobileTheme {
        StudentLogin(
            onLoginClick = {},
            emailAndPasswordClient = EmailAndPasswordClient(),
        )
    }
}
