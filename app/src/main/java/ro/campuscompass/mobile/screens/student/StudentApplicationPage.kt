package ro.campuscompass.mobile.screens.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel
import ro.campuscompass.mobile.screens.landlord.PropertiesList

@Composable
fun StudentApplicationPage(offerId: String) {
    val viewModel = getViewModel<StudentApplicationViewModel>().also { it.getAppliedProperty(offerId) }
    val propertyToDisplay = viewModel.takenProperty.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Name: ${propertyToDisplay.value.name}")
        Text("University: ${propertyToDisplay.value.universityName}")
        Text("Address: ${propertyToDisplay.value.address}")
        Text("Description: ${propertyToDisplay.value.description}")
        Text("Bedrooms: ${propertyToDisplay.value.bedrooms}")
        Text("Price per night: ${propertyToDisplay.value.price}")
    }
}