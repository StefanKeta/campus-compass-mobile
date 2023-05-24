package ro.campuscompass.mobile.screens.student

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun StudentMainPage(studentId: String = "", uniId: String = "", onOfferSelected: (String) -> Unit = {}) {
    val viewModel = getViewModel<StudentMainViewModel>().also { it.retrieveOffers(uniId) }
    val offers = viewModel.offers.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(offers.value) { offer ->
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                Text(text = offer.name, fontWeight = FontWeight.Bold)
                Button(onClick = { viewModel.takeOffer(studentId, offer.offerId, onOfferSelected) }) {
                    Text(text = "Reserve")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StudentMainPagePreview() {
    CampusCompassMobileTheme {
        StudentMainPage()
    }
}
