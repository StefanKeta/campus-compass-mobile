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
    ): ModelResult<List<(T)>> {
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

    suspend fun addDocument(
            collectionName: String,
            obj: Any,
    ): ModelResult<Unit> {
        return suspendCancellableCoroutine { cont ->
            db.collection(collectionName)
                    .add(obj)
                    .addOnSuccessListener { result ->
                        println("Document added with id ${result.id}")
                        cont.resumeWith(Result.success(ModelResult.success(Unit)))
                    }
                    .addOnFailureListener { exception ->
                        println("Error adding document: $exception")
                        cont.resumeWith(Result.success(ModelResult.error(exception.message ?: "")))
                    }
        }
    }

    suspend fun setDocument(collectionName: String, documentId: String, entity: Any): ModelResult<Unit> {
        return suspendCancellableCoroutine { cont ->
            db.collection(collectionName).document(documentId).set(entity)
                    .addOnSuccessListener {
                        println("Document added with id $documentId")
                        cont.resumeWith(Result.success(ModelResult.success(Unit)))
                    }
                    .addOnFailureListener { exception ->
                        println("Error adding document: $exception")
                        cont.resumeWith(Result.success(ModelResult.error(exception.message ?: "")))
                    }
        }
    }

    suspend fun updateDocument(collectionName: String, documentId: String, fieldsToUpdate: Map<String, Any>): ModelResult<Int> {
        return suspendCancellableCoroutine { cont ->
            db.collection(collectionName).document(documentId).update(fieldsToUpdate).addOnSuccessListener {
                cont.resumeWith(Result.success(ModelResult.success(1)))
            }
        }
    }


}
