package ro.campuscompass.mobile.screens.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
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
fun LandlordRegister(
    emailAndPasswordClient: EmailAndPasswordClient,
    onRegisterClick: (SignInResult) -> Unit,
    onAlreadyRegisteredClick: () -> Unit,
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isButtonEnabled by remember {
        derivedStateOf {
            isEmailValid(email) && password.isNotEmpty() && password == confirmPassword
        }
    }

    var registerInProgress by remember { mutableStateOf(false) }
    val registerCoroutineScope = rememberCoroutineScope()

    val registerError = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = registerError.value) {
        registerError.value?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    val onRegisterButtonClick: () -> Unit = {
        registerInProgress = true
        registerCoroutineScope.launch {
            val signInResult = emailAndPasswordClient.register(
                email = email,
                password = password,
            )

            if (signInResult.error == null) {
                onRegisterClick(signInResult)
                return@launch
            }
            registerError.value = signInResult.error
            registerInProgress = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthText(R.string.landlord_register)
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
                errorMessage = if (password.length < 6) stringResource(R.string.password_too_short) else null,
            )
            PasswordTextField(
                confirmPassword,
                onPasswordChange = { confirmPassword = it },
                errorMessage = if (password != confirmPassword) stringResource(R.string.passwords_dont_match) else null,
                label = stringResource(R.string.prompt_confirm_password)
            )
            Text(stringResource(R.string.already_registered),
                Modifier.clickable { onAlreadyRegisteredClick() })
            if (registerInProgress) {
                CircularProgressIndicator()
            }
            Button(
                enabled = isButtonEnabled,
                onClick = onRegisterButtonClick
            ) {
                Text(stringResource(R.string.register))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LandlordRegisterPreview() {
    CampusCompassMobileTheme {
        LandlordRegister(
            onRegisterClick = {},
            onAlreadyRegisteredClick = {},
            emailAndPasswordClient = EmailAndPasswordClient(),
        )
    }
}
