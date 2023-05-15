package ro.campuscompass.mobile.screens.student

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.models.Offer
import ro.campuscompass.mobile.services.student.StudentService

class StudentMainViewModel(private val studentService: StudentService) : ViewModel() {
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    fun retrieveOffers(universityId: String) {
        viewModelScope.launch {
            studentService.retrieveOffersForUniversity(universityId).onSuccess { off ->
                Log.d("Offers loaded", "retrieveOffers: $off")
                _offers.value = off
            }.onError {
                Log.e(TAG, "retrieveOffers: Unable to retrieve items")
            }
        }
    }

    fun takeOffer(studentId: String, offerId: String, onOfferTaken: (String) -> Unit) {
        viewModelScope.launch {
            studentService.assignStudentToOffer(studentId, offerId).onSuccess { onOfferTaken(offerId) }
        }
    }

    companion object {
        const val TAG = "StudentMainViewModel"
    }
}