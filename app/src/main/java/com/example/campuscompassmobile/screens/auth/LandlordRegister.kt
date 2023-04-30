package com.example.campuscompassmobile.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.campuscompassmobile.R
import com.example.campuscompassmobile.screens.utils.AuthText
import com.example.campuscompassmobile.ui.theme.CampusCompassMobileTheme

@Composable
fun LandlordRegister(
    onRegisterClick: () -> Unit,
    onAlreadyRegisteredClick: () -> Unit,
) {
    // TODO: Implement register functionality
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
            OutlinedTextField(value = email, onValueChange = { email = it }, label = {
                Text(stringResource(R.string.prompt_email))
            })
            OutlinedTextField(value = password, onValueChange = { password = it }, label = {
                Text(stringResource(R.string.prompt_password))
            })
            Text(stringResource(R.string.already_registered),
                Modifier.clickable { onAlreadyRegisteredClick() })
            Button(onClick = onRegisterClick) {
                Text(stringResource(R.string.register))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandlordRegisterPreview() {
    CampusCompassMobileTheme {
        LandlordRegister(
            onRegisterClick = {},
            onAlreadyRegisteredClick = {}
        )
    }
}