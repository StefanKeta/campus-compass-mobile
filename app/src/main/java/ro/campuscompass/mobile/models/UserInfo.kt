package ro.campuscompass.mobile.models

import com.google.firebase.firestore.DocumentId

data class UserInfo(@DocumentId val userId: String = "", val userType: UserType = UserType.UNKNOWN, val universityId: String = "", val username: String = "", val password: String = "")
