package ro.campuscompass.mobile.screens.student

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.repository.entities.LandLordPropertyEntity
import ro.campuscompass.mobile.services.student.StudentService


class StudentApplicationViewModel(private val studentService: StudentService) : ViewModel() {
    private val _property = MutableStateFlow<LandLordPropertyEntity>(LandLordPropertyEntity())
    val takenProperty: StateFlow<LandLordPropertyEntity> = _property

    fun getAppliedProperty(offerId: String) = viewModelScope.launch {
        studentService.retrieveAppliedProperty(offerId).onSuccess {
                    Log.d(TAG, "getAppliedOffer: $it")
                    _property.value = it
                }.onError {
                    Log.e(TAG, "getAppliedOffer: Failed with $it")
                }
    }

    companion object {
        const val TAG = "[StudentAppliedOffer]"
    }
}