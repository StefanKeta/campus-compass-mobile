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

@Composable
fun StudentApplicationPage(offerId: String) {
    val viewModel = getViewModel<StudentApplicationViewModel>().also { it.getAppliedOffer(offerId) }
    val propertyToDisplay = viewModel.takenOffer.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(propertyToDisplay.value.name)
        Text("Item 2")
        Text("Item 3")
        Text("Item 4")
        Text("Item 5")
    }
}