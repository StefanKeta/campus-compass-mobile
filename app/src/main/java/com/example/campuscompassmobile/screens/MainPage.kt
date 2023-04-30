package com.example.campuscompassmobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.campuscompassmobile.screens.utils.AuthText
import com.example.campuscompassmobile.ui.theme.CampusCompassMobileTheme

@Composable
fun MainPage(
    studentLoginClick: () -> Unit,
    landlordLoginClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthText(R.string.app_name)
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = studentLoginClick) {
                Text(stringResource(R.string.student_login))
            }
            Button(onClick = landlordLoginClick) {
                Text(stringResource(R.string.landlord_login))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    CampusCompassMobileTheme {
        MainPage(
            studentLoginClick = {},
            landlordLoginClick = {}
        )
    }
}