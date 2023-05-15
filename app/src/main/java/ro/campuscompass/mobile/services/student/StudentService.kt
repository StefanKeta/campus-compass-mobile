package ro.campuscompass.mobile.services.student

import android.util.Log
import ro.campuscompass.mobile.models.ModelResult
import ro.campuscompass.mobile.models.Offer
import ro.campuscompass.mobile.repository.Repository
import java.util.Optional

const val OFFERS_COL = "offers"

class StudentService(private val repository: Repository) {
    suspend fun retrieveOffersForUniversity(universityId: String): ModelResult<List<Offer>> {
        return repository.getCollectionByFilter(OFFERS_COL, Offer::class.java, "universityId", universityId).map { it -> it.filter { it.takenBy.isEmpty() } }.also { Log.d("Loading offers", "retrieveOffersForUniversity: ") }
    }

    suspend fun assignStudentToOffer(studentId: String, offerId: String): ModelResult<Int> {
        Log.d("OfferAssigning", "assignStudentToOffer: $offerId")
        return repository.updateDocument(OFFERS_COL, offerId, mapOf(Pair("takenBy", studentId)))
    }
}