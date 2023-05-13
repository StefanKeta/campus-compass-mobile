package ro.campuscompass.mobile.screens.utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NumberTextField(
        label: String,
        requiredLabel: String,
        value: Int?,
        onValueChange: (Int?) -> Unit,
) {
    var valueWasModified by remember { mutableStateOf(false) }

    val hasError = valueWasModified && value == null

    OutlinedTextField(
            value = value?.toString() ?: "",
            onValueChange = { it ->
                if (it.isEmpty()) {
                    onValueChange(null)
                } else {
                    it.toIntOrNull()?.let { onValueChange(it) }
                }
                valueWasModified = true
            },
            singleLine = true,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = hasError,
        supportingText = {
            if (hasError) {
                Text(
                    requiredLabel,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun NumberTextFieldPreview() {
    var value: Int? by remember { mutableStateOf(null) }
    NumberTextField("Value", "Value is required", value) { value = it }
}
