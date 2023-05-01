package ro.campuscompass.mobile.screens.student

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun StudentMainPage() {
    Text(text = "Student main page")
}

@Preview(showBackground = true)
@Composable
fun StudentMainPagePreview() {
    CampusCompassMobileTheme {
        StudentMainPage()
    }
}
