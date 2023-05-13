package ro.campuscompass.mobile.screens.landlord

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun LandlordMainPage(onAddPropertyClick: () -> Unit) {
    val viewModel = getViewModel<LandLordPropertiesViewModel>()
    val properties = viewModel.properties.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProperties()
    }

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text(stringResource(R.string.landlord_page_title)) },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddPropertyClick) {
                    Icon(imageVector = Icons.Rounded.Add,
                            contentDescription = stringResource(id = R.string.landlord_add_property))
                }
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    Column(modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isLoading.value) {
                            Text(text = "Loading Properties...")
                            CircularProgressIndicator()
                        } else {
                            if (errorMessage.value != null) {
                                Text(text = stringResource(R.string.landlord_page_error_message), color = MaterialTheme.colorScheme.error)
                            } else {
                                PropertiesList(properties.value)
                            }
                        }
                    }
                }
            },
    )
}

@Preview(showBackground = true)
@Composable
fun LandlordMainPagePreview() {
    CampusCompassMobileTheme {
        LandlordMainPage { }
    }
}
