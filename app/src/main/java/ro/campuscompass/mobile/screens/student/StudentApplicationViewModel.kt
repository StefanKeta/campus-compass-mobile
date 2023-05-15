package ro.campuscompass.mobile.screens.student

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.models.Offer
import ro.campuscompass.mobile.services.student.StudentService


class StudentApplicationViewModel(private val studentService: StudentService) : ViewModel() {
    private val _offer = MutableStateFlow<Offer>(Offer())
    val takenOffer: StateFlow<Offer> = _offer

    fun getAppliedOffer(offerId: String) = viewModelScope.launch {
        studentService.retrieveAppliedOffer(offerId).onSuccess {
                    Log.d(TAG, "getAppliedOffer: $it")
                    _offer.value = it
                }.onError {
                    Log.e(TAG, "getAppliedOffer: Failed with $it")
                }
    }

    companion object {
        const val TAG = "[StudentAppliedOffer]"
    }
}