package ro.campuscompass.mobile.screens.landlord.addproperty

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun LandlordAddPropertyPage(onAddPropertyClick: () -> Unit) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text(stringResource(R.string.landlord_add_property_page_title)) },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                )
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    AddPropertyForm { onAddPropertyClick() }
                }
            },
    )
}

@Preview(showBackground = true)
@Composable
fun LandlordAddPropertyPagePreview() {
    CampusCompassMobileTheme {
        LandlordAddPropertyPage {}
    }
}
