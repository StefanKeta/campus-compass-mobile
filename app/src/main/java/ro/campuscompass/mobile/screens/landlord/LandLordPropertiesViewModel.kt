package ro.campuscompass.mobile.screens.landlord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.models.LandLordProperty
import ro.campuscompass.mobile.services.auth.LandlordService

class LandLordPropertiesViewModel(
        private val landlordService: LandlordService,
) : ViewModel() {
    val properties = MutableStateFlow<List<LandLordProperty>>(emptyList())
    val isLoading = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String?>(null)

    fun getProperties() {
        viewModelScope.launch {
            isLoading.value = true
            landlordService.getProperties()
                    .onSuccess {
                        properties.value = it
                        errorMessage.value = null
                    }.onError {
                        errorMessage.value = it
                    }
            isLoading.value = false
        }
    }
}
