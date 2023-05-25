package ro.campuscompass.mobile.services.auth

import ro.campuscompass.mobile.models.ModelResult
import ro.campuscompass.mobile.models.SignInResult
import ro.campuscompass.mobile.models.UserInfo
import ro.campuscompass.mobile.models.UserType
import ro.campuscompass.mobile.repository.Repository
import ro.campuscompass.mobile.repository.entities.UserInfoEntity

private const val USERS = "users"

class AuthenticationService(
        private val emailAndPasswordClient: EmailAndPasswordClient,
        private val repository: Repository,
) {
    suspend fun studentLogin(email: String, password: String): ModelResult<UserInfo> {
        return login(email, password).flatmap {
            if (it.userType == UserType.STUDENT) {
                ModelResult.success(it)
            } else {
                println("User is not a landlord, but ${it.userType}")
                ModelResult.error("Invalid email or password")
            }
        }
    }

    suspend fun loginAsLandlord(email: String, password: String): ModelResult<UserInfo> {
        return login(email, password).flatmap {
            if (it.userType == UserType.LANDLORD) {
                ModelResult.success(it)
            } else {
                println("User is not a landlord, but ${it.userType}")
                ModelResult.error("Invalid email or password")
            }
        }
    }

    fun logout() {
        emailAndPasswordClient.logout()
    }

    suspend fun registerLandlord(email: String, password: String): ModelResult<UserInfo> {
        return emailAndPasswordClient.register(email, password).map {
            UserInfo(
                    userId = it.userId,
                    userType = UserType.LANDLORD,
            )
        }.flatmap { userInfo ->
            repository.setDocument(USERS, userInfo.userId, UserInfoEntity(userType = userInfo.userType, universityId = userInfo.universityId)).map {
                userInfo
            }
        }
    }

    suspend fun getStudentUser(email: String, password: String): ModelResult<UserInfoEntity?> {
        return repository.getCollection(USERS, UserInfoEntity::class.java).map { list ->
            list.find { userInfoEntity -> userInfoEntity.username == email && password == userInfoEntity.password }
        }
    }

    suspend fun createUser(email: String, password: String): ModelResult<SignInResult> {
        return emailAndPasswordClient.register(email, password)
    }

    private suspend fun login(email: String, password: String): ModelResult<UserInfo> {
        return emailAndPasswordClient.login(email, password).flatmap { signInResult ->
            println("Fetching user info for ${signInResult.userId}")
            repository.getDocument(USERS, signInResult.userId, UserInfoEntity::class.java).map { document ->
                println("User is $document")
                UserInfo(userId = signInResult.userId, userType = document.userType, universityId = document.universityId, password = document.password, username = document.username)
            }
        }
    }
}
