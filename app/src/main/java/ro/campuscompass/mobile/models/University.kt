package ro.campuscompass.mobile.models

import com.google.firebase.firestore.DocumentId

data class University(@DocumentId val id: String, val name: String)
