package ro.campuscompass.mobile.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ro.campuscompass.mobile.models.UserInfo
import ro.campuscompass.mobile.models.UserType
import ro.campuscompass.mobile.services.auth.AuthenticationService

class SignInViewModel(private val authenticationService: AuthenticationService) : ViewModel() {
    private val userInfo = MutableStateFlow<UserInfo?>(null)
    val isLoading = MutableStateFlow(false)

    fun loginAsStudent(
            email: String,
            password: String,
            onSuccess: (String, String) -> Unit,
            onError: (errorMessage: String) -> Unit,
    ) {
        viewModelScope.launch {
            isLoading.value = true
            authenticationService.getStudentUser(email, password).onSuccess {
                if (it != null) {
                    userInfo.value = userInfo.value?.copy(userId = it.userId, userType = UserType.STUDENT, universityId = it.universityId, password = it.password, username = it.username)
                    onSuccess(it.userId, it.universityId)
                }
            }.onError { onError("Student does not exist!") }
        }
        isLoading.value = false
    }

    fun loginAsLandlord(
            email: String,
            password: String,
            onSuccess: () -> Unit,
            onError: (errorMessage: String) -> Unit,
    ) {
        viewModelScope.launch {
            isLoading.value = true
            authenticationService.loginAsLandlord(email, password).onSuccess {
                userInfo.value = userInfo.value?.copy(
                        userId = it.userId,
                        userType = it.userType,
                )
                onSuccess()
            }.onError {
                onError(it)
            }
            isLoading.value = false
        }
    }

    fun registerAsLandlord(
            email: String,
            password: String,
            onSuccess: () -> Unit,
            onError: (errorMessage: String) -> Unit,
    ) {
        viewModelScope.launch {
            isLoading.value = true
            authenticationService.registerLandlord(email, password).onSuccess {
                userInfo.value = userInfo.value?.copy(
                        userId = it.userId,
                        userType = it.userType,
                )
                onSuccess()
            }.onError {
                onError(it)
            }
            isLoading.value = false
        }
    }

    fun resetState() {
        isLoading.value = false
        userInfo.value = null
    }
}
