package ro.campuscompass.mobile.repository.entities

import ro.campuscompass.mobile.models.UserType

data class UserInfoEntity(
    val userType: UserType,
) {
    constructor() : this(UserType.UNKNOWN)
}
