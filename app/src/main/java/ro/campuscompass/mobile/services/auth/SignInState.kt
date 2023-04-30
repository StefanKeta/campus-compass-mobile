package ro.campuscompass.mobile.services.auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)
