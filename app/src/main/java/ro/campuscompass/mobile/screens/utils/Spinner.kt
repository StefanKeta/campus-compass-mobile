package ro.campuscompass.mobile.screens.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.campuscompass.mobile.ui.theme.CampusCompassMobileTheme

data class SpinnerOption(val text: String, val value: String)

@Composable
fun <T> Spinner(
        modifier: Modifier = Modifier,
        dropDownModifier: Modifier = Modifier,
        items: List<T>,
        selectedItem: T?,
        onItemSelected: (T) -> Unit,
        selectedItemFactory: @Composable (Modifier, T?) -> Unit,
        dropdownItemFactory: @Composable (T, Int) -> Unit,
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.TopStart)) {
        selectedItemFactory(Modifier.clickable { expanded = true },
                selectedItem
        )
        DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = dropDownModifier
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(onClick = {
                    onItemSelected(items[index])
                    expanded = false
                }, text = {
                    dropdownItemFactory(element, index)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineSpinnerPreview() {
    val selectedOption = remember { mutableStateOf<SpinnerOption?>(null) }
    CampusCompassMobileTheme {
        Spinner(items = listOf(
                SpinnerOption("Food", "Food"),
                SpinnerOption("Bill Payment", "Bill Payment"),
                SpinnerOption("Recharges", "Recharges"),
                SpinnerOption("Outing", "Outing"),
                SpinnerOption("Other", "Other"
                )),
                selectedItem = selectedOption.value,
                onItemSelected = {
                    selectedOption.value = it
                },
                selectedItemFactory = { modifier, item ->
                    Row(
                            modifier = modifier
                                    .padding(8.dp)
                                    .wrapContentSize()
                    ) {
                        Text(text = item?.text ?: "Select an option")
                        Icon(Icons.Outlined.ArrowDropDown, null)
                    }
                },
                dropdownItemFactory = { item, _ ->
                    Text(text = item.text)
                }
        )
    }
}
