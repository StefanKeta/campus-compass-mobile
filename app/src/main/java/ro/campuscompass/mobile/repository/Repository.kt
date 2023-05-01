package ro.campuscompass.mobile.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import ro.campuscompass.mobile.models.ModelResult

class Repository {
    private val db = Firebase.firestore

    suspend fun <T> getCollection(
        collectionName: String,
        clazz: Class<T>,
    ): ModelResult<List<T>> {
        return suspendCancellableCoroutine { cont ->
            db.collection(collectionName)
                .get()
                .addOnSuccessListener { result ->
                    val list = result.toObjects(clazz)
                    cont.resumeWith(Result.success(ModelResult.success(list)))
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                    cont.resumeWith(Result.success(ModelResult.error(exception.message)))
                }
        }
    }

    suspend fun <T> getDocument(
        collectionName: String,
        documentId: String,
        clazz: Class<T>,
    ): ModelResult<T> {
        return suspendCancellableCoroutine { cont ->
            db.collection(collectionName)
                .document(documentId)
                .get()
                .addOnSuccessListener { result ->
                    val obj = result.toObject(clazz)
                    cont.resumeWith(Result.success(ModelResult.success(obj!!)))
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                    cont.resumeWith(Result.success(ModelResult.error(exception.message ?: "")))
                }
        }
    }
}
