package ro.campuscompass.mobile.screens.utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ro.campuscompass.mobile.R

@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
) {
    var emailFieldWasTouched by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
            emailFieldWasTouched = true
        },
        label = { Text(stringResource(R.string.prompt_email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}

fun isEmailValid(email: String): Boolean {
    return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    var email by remember { mutableStateOf("") }
    EmailTextField(email, { email = it })
}
