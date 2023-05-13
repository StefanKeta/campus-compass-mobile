package ro.campuscompass.mobile.screens.landlord

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.models.LandLordProperty
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun PropertiesList(properties: List<LandLordProperty>) {
    val state = rememberLazyListState()

    if (properties.isEmpty()) {
        Text(stringResource(R.string.landlord_no_properties))
    } else {
        LazyColumn(
                modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                state = state,
        ) {
            items(properties.size) { index ->
                PropertyListItem(properties[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertiesListPreview() {
    CampusCompassMobileTheme {
        PropertiesList(
                List(10) {
                    LandLordProperty(it.toString(), "name $it", "address $it", "description $it", 100, 2, "universityId", "universityName", "image", "availableFrom")
                }.toList()
        )
    }
}
