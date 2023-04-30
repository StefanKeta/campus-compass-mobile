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
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme


@Composable
fun LandlordLogin(
    emailAndPasswordClient: EmailAndPasswordClient,
    onLoginClick: () -> Unit,
    onDontHaveAccountClick: () -> Unit,
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignInInProgress by remember { mutableStateOf(false) }
    val loginCoroutineScope = rememberCoroutineScope()

    val signInError = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = signInError.value) {
        signInError.value?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    val onLoginButtonClick: () -> Unit = {
        isSignInInProgress = true
        loginCoroutineScope.launch {
            val (_, error) = emailAndPasswordClient.login(
                email = email,
                password = password,
            )

            if (error == null) {
                onLoginClick()
                return@launch
            }
            signInError.value = error
            isSignInInProgress = false
        }
    }

    val isButtonEnabled by remember {
        derivedStateOf {
            isEmailValid(email) && password.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthText(R.string.landlord_login)
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
            Text(
                stringResource(R.string.dont_have_account),
                Modifier.clickable { onDontHaveAccountClick() })
            if (isSignInInProgress) {
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
private fun LandlordLoginPreview() {
    CampusCompassMobileTheme {
        LandlordLogin(
            onLoginClick = {},
            onDontHaveAccountClick = {},
            emailAndPasswordClient = EmailAndPasswordClient()
        )
    }
}
