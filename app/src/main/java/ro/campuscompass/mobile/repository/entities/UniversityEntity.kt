package ro.campuscompass.mobile.repository.entities

import com.google.firebase.firestore.DocumentId

data class UniversityEntity(@DocumentId val id: String, val name: String) {
    constructor() : this("", "")
}
