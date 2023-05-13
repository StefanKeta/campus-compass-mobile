package ro.campuscompass.mobile.screens.landlord.addproperty

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ro.campuscompass.mobile.R
import ro.campuscompass.mobile.screens.utils.NumberTextField
import ro.campuscompass.mobile.screens.utils.RequiredTextField
import ro.campuscompass.mobile.screens.utils.Spinner
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

@Composable
fun AddPropertyForm(onAddPropertyClick: () -> Unit) {
    val context = LocalContext.current
    val viewModel = getViewModel<AddPropertyViewModel>()
    val name = viewModel.name.collectAsState()
    val address = viewModel.address.collectAsState()
    val description = viewModel.description.collectAsState()
    val price = viewModel.price.collectAsState()
    val bedrooms = viewModel.bedrooms.collectAsState()
    val isLoadingUniversities = viewModel.isLoadingUniversities.collectAsState()
    val universities = viewModel.universities.collectAsState()
    val selectedUniversity = viewModel.selectedUniversity.collectAsState()
    val isLoadingAddProperty = viewModel.isLoadingAddProperty.collectAsState()

    val isAddButtonEnabled = name.value != null && address.value != null &&
            description.value != null && price.value != null && bedrooms.value != null && selectedUniversity.value != null

    LaunchedEffect(Unit) {
        viewModel.getUniversities()
    }

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RequiredTextField(label = stringResource(R.string.landlord_property_name),
                requiredLabel = stringResource(R.string.landlord_property_name_required),
                value = name.value, onValueChange = { name -> viewModel.onNameChange(name) })
        RequiredTextField(label = stringResource(R.string.landlord_property_address),
                requiredLabel = stringResource(R.string.landlord_property_address_required),
                value = address.value, onValueChange = { address -> viewModel.onAddressChange(address) })
        RequiredTextField(label = stringResource(R.string.landlord_property_description),
                requiredLabel = stringResource(R.string.landlord_property_description_required),
                value = description.value, onValueChange = { description -> viewModel.onDescriptionChange(description) })
        NumberTextField(label = stringResource(R.string.landlord_property_price),
                requiredLabel = stringResource(R.string.landlord_property_price_required),
                value = price.value, onValueChange = { price -> viewModel.onPriceChange(price) })
        NumberTextField(label = stringResource(R.string.landlord_property_bedrooms),
                requiredLabel = stringResource(R.string.landlord_property_bedrooms_required),
                value = bedrooms.value, onValueChange = { bedrooms -> viewModel.onBedroomsChange(bedrooms) })
        if (isLoadingUniversities.value) {
            CircularProgressIndicator()
        } else {
            Spinner(
                    items = universities.value,
                    selectedItem = selectedUniversity.value,
                    onItemSelected = { university -> viewModel.onUniversityChange(university) },
                    selectedItemFactory = { modifier, university ->
                        Row(modifier = modifier
                                .padding(8.dp)
                                .wrapContentSize()
                        ) {
                            Text(text = university?.name ?: "Select an university")
                            Icon(Icons.Outlined.ArrowDropDown, null)
                        }
                    },
                    dropdownItemFactory = { university, _ ->
                        Text(text = university.name)
                    }
            )
        }
        if (isLoadingAddProperty.value) {
            CircularProgressIndicator()
        } else {
            Button(
                    enabled = isAddButtonEnabled,
                    onClick = {
                        viewModel.addProperty(onSuccess = {
                            onAddPropertyClick()
                        }, onError = {
                            Toast.makeText(
                                    context, it, Toast.LENGTH_LONG
                            ).show()
                        })
                    },
            ) {
                Text(stringResource(R.string.landlord_add_property))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPropertyFormPreview() {
    CampusCompassMobileTheme {
        AddPropertyForm {}
    }
}
