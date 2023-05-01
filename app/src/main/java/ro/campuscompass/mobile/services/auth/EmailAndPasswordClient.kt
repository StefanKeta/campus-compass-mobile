package ro.campuscompass.mobile.services.auth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ro.campuscompass.mobile.models.ModelResult
import ro.campuscompass.mobile.models.SignInResult
import java.util.concurrent.CancellationException

class EmailAndPasswordClient {
    private val auth = Firebase.auth

    fun getCurrentUser(): ModelResult<SignInResult>? =
        auth.currentUser?.let { ModelResult.success(SignInResult(it.uid)) }

    suspend fun login(email: String, password: String): ModelResult<SignInResult> {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password)
                .await()
                .user ?: return ModelResult.error("User is null")

            ModelResult.success(SignInResult(user.uid))
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
            ModelResult.error(e.message)
        }
    }

    fun logout() {
        auth.signOut()
    }

    suspend fun register(email: String, password: String): ModelResult<SignInResult> {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password)
                .await().user ?: return ModelResult.error("User is null")

            ModelResult.success(SignInResult(user.uid))
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
            ModelResult.error(e.message)
        }
    }
}
