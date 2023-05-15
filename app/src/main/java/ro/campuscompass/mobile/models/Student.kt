package ro.campuscompass.mobile.models

import com.google.firebase.firestore.DocumentId

data class Student(@DocumentId val id:String="",val email:String="",val universityId:String="")
