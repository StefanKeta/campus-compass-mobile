package ro.campuscompass.mobile.screens.landlord

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.models.LandLordProperty
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun PropertyListItem(property: LandLordProperty) {
    Card(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(id = R.string.landlord_property_name),
                        fontWeight = FontWeight.Bold
                )
                Text(text = property.name)
            }
            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(id = R.string.landlord_property_address),
                        fontWeight = FontWeight.Bold)
                Text(text = property.address)
            }
            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(id = R.string.landlord_property_price),
                        fontWeight = FontWeight.Bold)
                Text(text = property.price.toString())
            }
            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(id = R.string.landlord_property_bedrooms),
                        fontWeight = FontWeight.Bold)
                Text(text = property.bedrooms.toString())
            }
            Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(id = R.string.landlord_property_description),
                        fontWeight = FontWeight.Bold)
                Text(text = property.description)
            }
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(id = R.string.landlord_property_available_from),
                    fontWeight = FontWeight.Bold)
            Text(text = property.universityName)
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyListItemPreview() {
    CampusCompassMobileTheme {
        PropertyListItem(property =
        LandLordProperty(
                id = "1",
                name = "Property 1",
                address = "Address 1",
                description = "Description 1",
                bedrooms = 2,
                price = 20,
                image = "Image 1",
                availableFrom = "30 May 2023",
                universityId = "1",
                universityName = "University 1",
                isTakenBy = "userId"
        ))
    }
}
