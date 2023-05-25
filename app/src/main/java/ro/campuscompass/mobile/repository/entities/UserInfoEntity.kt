package ro.campuscompass.mobile.repository.entities

import com.google.firebase.firestore.DocumentId
import ro.campuscompass.mobile.models.UserType

data class UserInfoEntity(
        @DocumentId val userId: String = "", val userType: UserType = UserType.UNKNOWN, val universityId: String = "", val username: String = "", val password: String = "",
)