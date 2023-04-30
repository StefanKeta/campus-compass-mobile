package com.example.campuscompassmobile.screens.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.campuscompassmobile.R

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    errorMessage: String? = null,
    label: String = stringResource(R.string.prompt_password),
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordFieldWasTouched by remember { mutableStateOf(false) }

    val hasError =
        (passwordFieldWasTouched && (password.isEmpty())) || errorMessage != null

    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChange(it)
            passwordFieldWasTouched = true
        },
        label = { Text(label) },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image: ImageVector
            val description: String

            if (passwordVisibility) {
                image = Icons.Filled.Visibility
                description = stringResource(R.string.hide_password)
            } else {
                image = Icons.Filled.VisibilityOff
                description = stringResource(R.string.show_password)
            }

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(image, description)
            }
        },
        isError = hasError,
        supportingText = {
            if (hasError) {
                Text(
                    errorMessage ?: stringResource(R.string.required_password_field),
                    color = MaterialTheme.colorScheme.error
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    var password by remember { mutableStateOf("") }
    PasswordTextField("", { password = it })
}
