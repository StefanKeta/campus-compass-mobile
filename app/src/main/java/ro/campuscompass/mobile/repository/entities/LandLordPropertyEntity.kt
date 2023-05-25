package ro.campuscompass.mobile.repository.entities

import com.google.firebase.firestore.DocumentId

data class LandLordPropertyEntity(
        @DocumentId val id: String,
        val name: String,
        val address: String,
        val description: String,
        val price: Int,
        val bedrooms: Int,
        val universityId: String,
        val universityName: String,
        val isTakenBy: String,
        val image: String?,
        val availableFrom: String?,
) {
    constructor() : this("", "", "", "", 0, 0, "", "", "", null, null)
}
