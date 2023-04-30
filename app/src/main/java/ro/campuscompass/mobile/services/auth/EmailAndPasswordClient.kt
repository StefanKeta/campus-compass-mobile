package ro.campuscompass.mobile.services.auth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class EmailAndPasswordClient(
) {
    private val auth = Firebase.auth

    fun getCurrentUser() = auth.currentUser?.run {
        UserData(
            userId = uid
        )
    }

    suspend fun login(email: String, password: String): SignInResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password)
                .await()
                .user
            SignInResult(
                userData = user?.run {
                    UserData(
                        userId = uid
                    )
                },
                error = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
            SignInResult(
                userData = null,
                error = e.message
            )
        }
    }

    fun logout() {
        auth.signOut()
    }

    suspend fun register(email: String, password: String): SignInResult {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password)
                .await().user
            SignInResult(
                userData = user?.run {
                    UserData(
                        userId = uid
                    )
                },
                error = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
            SignInResult(
                userData = null,
                error = e.message
            )
        }
    }
}
