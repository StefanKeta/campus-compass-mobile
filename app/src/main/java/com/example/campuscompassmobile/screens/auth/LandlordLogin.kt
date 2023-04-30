package com.example.campuscompassmobile.screens.auth

import androidx.compose.foundation.clickable
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
import com.example.campuscompassmobile.R
import com.example.campuscompassmobile.screens.utils.AuthText
import com.example.campuscompassmobile.screens.utils.EmailTextField
import com.example.campuscompassmobile.screens.utils.PasswordTextField
import com.example.campuscompassmobile.screens.utils.isEmailValid
import com.example.campuscompassmobile.ui.theme.CampusCompassMobileTheme


@Composable
fun LandlordLogin(
    onLoginClick: () -> Unit,
    onDontHaveAccountClick: () -> Unit,
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
            Text(stringResource(R.string.dont_have_account),
                Modifier.clickable { onDontHaveAccountClick() })
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
private fun LandlordLoginPreview() {
    CampusCompassMobileTheme {
        LandlordLogin(
            onLoginClick = {},
            onDontHaveAccountClick = {}
        )
    }
}
