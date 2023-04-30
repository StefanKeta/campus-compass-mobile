package ro.campuscompass.mobile.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.screens.utils.AuthText
import ro.campuscompass.mobile.screens.utils.EmailTextField
import ro.campuscompass.mobile.screens.utils.PasswordTextField
import ro.campuscompass.mobile.screens.utils.isEmailValid
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme


@Composable
fun StudentLogin(
    onLoginClick: () -> Unit,
) {
    // TODO: Implement login functionality
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            Button(
                enabled = isButtonEnabled,
                onClick = onLoginClick
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
            onLoginClick = {}
        )
    }
}
