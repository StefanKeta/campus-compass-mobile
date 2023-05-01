package ro.campuscompass.mobile.screens.landlord

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun LandlordMainPage() {
    Text(text = "Landlord main page")
}

@Preview(showBackground = true)
@Composable
fun LandlordMainPagePreview() {
    CampusCompassMobileTheme {
        LandlordMainPage()
    }
}
