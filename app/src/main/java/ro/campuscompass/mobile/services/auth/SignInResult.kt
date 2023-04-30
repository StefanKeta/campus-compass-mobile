package ro.campuscompass.mobile.services.auth

data class SignInResult(
    val userData: UserData?,
    val error: String?
)

data class UserData(
    val userId: String,
)
