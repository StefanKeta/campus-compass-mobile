package ro.campuscompass.mobile.models

import com.fasterxml.jackson.annotation.JsonValue

enum class UserType(@JsonValue val value: String) {
    UNKNOWN("UNKNOWN"),
    STUDENT("STUDENT"),
    LANDLORD("LANDLORD")
}
