package ro.campuscompass.mobile.models

sealed class ModelResult<T> private constructor() {

    suspend fun <R> map(transform: suspend (T) -> R): ModelResult<R> {
        return try {
            when (this) {
                is Success -> Success(transform(data))
                is Error -> Error(error)
            }
        } catch (e: Exception) {
            Error(e.message ?: "")
        }
    }

    suspend fun <R> flatmap(transform: suspend (T) -> ModelResult<R>): ModelResult<R> {
        return try {
            when (this) {
                is Success -> transform(data)
                is Error -> Error(error)
            }
        } catch (e: Exception) {
            Error(e.message ?: "")
        }
    }

    suspend fun onSuccess(action: suspend (T) -> Unit): ModelResult<T> {
        if (this is Success) {
            action(data)
        }
        return this
    }

    suspend fun onError(action: suspend (String) -> Unit): ModelResult<T> {
        if (this is Error) {
            action(error)
        }
        return this
    }

    private data class Success<T>(val data: T) : ModelResult<T>()
    private data class Error<T>(val error: String) : ModelResult<T>()


    companion object {
        fun <T> success(data: T): ModelResult<T> = Success(data)
        fun <T> error(error: String?): ModelResult<T> = Error(error ?: "")
    }
}
