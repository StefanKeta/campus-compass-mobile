package ro.campuscompass.mobile.screens.landlord.addproperty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.models.University
import ro.campuscompass.mobile.repository.entities.AddLandLordPropertyEntity
import ro.campuscompass.mobile.services.auth.LandlordService
import ro.campuscompass.mobile.services.auth.UniversityService

class AddPropertyViewModel(
        private val universityService: UniversityService,
        private val landlordService: LandlordService,
) : ViewModel() {
    val name = MutableStateFlow<String?>(null)
    val address = MutableStateFlow<String?>(null)
    val description = MutableStateFlow<String?>(null)
    val price = MutableStateFlow<Int?>(0)
    val bedrooms = MutableStateFlow<Int?>(1)
    val universities = MutableStateFlow<List<University>>(emptyList())
    val selectedUniversity = MutableStateFlow<University?>(null)
    val isLoadingUniversities = MutableStateFlow(false)
    val loadingUniversityError = MutableStateFlow<String?>(null)
    val isLoadingAddProperty = MutableStateFlow(false)
    val loadingAddPropertyError = MutableStateFlow<String?>(null)

    fun onNameChange(name: String?) {
        this.name.value = name
    }

    fun onAddressChange(address: String?) {
        this.address.value = address
    }

    fun onDescriptionChange(description: String?) {
        this.description.value = description
    }

    fun onPriceChange(price: Int?) {
        this.price.value = price
    }

    fun onBedroomsChange(bedrooms: Int?) {
        this.bedrooms.value = bedrooms
    }

    fun getUniversities() {
        viewModelScope.launch {
            isLoadingUniversities.value = true
            universityService.getUniversities()
                    .onSuccess {
                        universities.value = it
                        loadingUniversityError.value = null
                    }.onError {
                        loadingUniversityError.value = it
                    }
            isLoadingUniversities.value = false
        }
    }

    fun addProperty(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            isLoadingAddProperty.value = true
            landlordService.addProperty(AddLandLordPropertyEntity(
                    name = name.value!!,
                    address = address.value!!,
                    description = description.value!!,
                    price = price.value!!,
                    bedrooms = bedrooms.value!!,
                    universityId = selectedUniversity.value!!.id,
                    universityName = selectedUniversity.value!!.name,
            )).onSuccess {
                loadingAddPropertyError.value = null
                onSuccess()
            }.onError {
                loadingAddPropertyError.value = it
                onError(it)
            }
            isLoadingAddProperty.value = false
        }
    }

    fun onUniversityChange(university: University?) {
        this.selectedUniversity.value = university
    }
}
