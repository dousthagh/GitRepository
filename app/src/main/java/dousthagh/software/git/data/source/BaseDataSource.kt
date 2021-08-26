package dousthagh.software.git.data.source

import dousthagh.software.git.util.Resource
import retrofit2.Response
import kotlin.Exception

abstract class BaseDataSource {
    protected suspend fun <T> getResponse(
        request: suspend () -> Response<T>
    ): Resource<T> {
        try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return Resource.error("${response.code()} ${response.message()}")

        } catch (e: Exception) {
            return Resource.error(message = e.message!!)
        }
    }
}