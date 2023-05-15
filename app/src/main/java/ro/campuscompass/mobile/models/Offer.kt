package ro.campuscompass.mobile.models

import com.google.firebase.firestore.DocumentId

data class Offer(@DocumentId val offerId: String = "", val name: String = "", val universityId: String = "", val takenBy: String = "")
