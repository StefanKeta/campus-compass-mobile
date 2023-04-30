package ro.campuscompass.mobile.screens.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthText(
    textId: Int,
) {
    Text(
        stringResource(id = textId),
        fontSize = 30.sp,
        modifier = Modifier.padding(top = 64.dp, bottom = 64.dp)
    )
}
