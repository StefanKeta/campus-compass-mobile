package ro.campuscompass.mobile.screens.student

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.models.Offer
import ro.campuscompass.mobile.repository.entities.LandLordPropertyEntity
import ro.campuscompass.mobile.services.student.StudentService

class StudentMainViewModel(private val studentService: StudentService) : ViewModel() {
    private val _properties = MutableStateFlow<List<LandLordPropertyEntity>>(emptyList())
    val properties: StateFlow<List<LandLordPropertyEntity>> = _properties

    fun retrieveOffers(universityId: String) {
        viewModelScope.launch {
            studentService.retrievePropertiesForUniversity(universityId).onSuccess { off ->
                Log.d("Offers loaded", "retrieveOffers: $off")
                _properties.value = off
            }.onError {
                Log.e(TAG, "retrieveOffers: Unable to retrieve items")
            }
        }
    }

    fun takeOffer(studentId: String, offerId: String, onOfferTaken: (String) -> Unit) {
        viewModelScope.launch {
            studentService.assignStudentToProperty(studentId, offerId).onSuccess { onOfferTaken(offerId) }
        }
    }

    fun checkIfStudentTookOffer(studentId: String, onSuccess: (LandLordPropertyEntity?) -> Unit) {
        viewModelScope.launch {
            studentService.checkIfStudentApplied(studentId).onSuccess {
                onSuccess(it)
            }
        }
    }

    companion object {
        const val TAG = "StudentMainViewModel"
    }
}