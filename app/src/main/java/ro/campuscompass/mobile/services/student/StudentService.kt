package ro.campuscompass.mobile.services.student

import android.util.Log
import ro.campuscompass.mobile.models.LandLordProperty
import ro.campuscompass.mobile.models.ModelResult
import ro.campuscompass.mobile.models.Offer
import ro.campuscompass.mobile.repository.Repository
import ro.campuscompass.mobile.repository.entities.LandLordPropertyEntity

const val PROPERTIES = "landlord_properties"

class StudentService(private val repository: Repository) {
    suspend fun retrievePropertiesForUniversity(universityId: String): ModelResult<List<LandLordPropertyEntity>> {
        return repository.getCollectionByFilter(PROPERTIES, LandLordPropertyEntity::class.java, Pair("universityId", universityId)).map { it.filter { prop -> prop.isTakenBy.isEmpty() } }.also { Log.d("Loading offers", "retrieveOffersForUniversity: ") }
    }

    suspend fun assignStudentToProperty(studentId: String, propertyId: String): ModelResult<Int> {
        Log.d("OfferAssigning", "assignStudentToOffer: $propertyId")
        return repository.updateDocument(PROPERTIES, propertyId, mapOf(Pair("isTakenBy", studentId)))
    }

    suspend fun retrieveAppliedProperty(propertyId: String): ModelResult<LandLordPropertyEntity> {
        return repository.getDocument(PROPERTIES, propertyId, LandLordPropertyEntity::class.java)
    }

    suspend fun checkIfStudentApplied(studentId: String): ModelResult<LandLordPropertyEntity?> {
        return repository.getDocumentByFilter(PROPERTIES, "isTakenBy", studentId, LandLordPropertyEntity::class.java)
    }
}