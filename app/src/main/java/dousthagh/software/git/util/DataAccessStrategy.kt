package dousthagh.software.git.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


fun <T> searchOperation(
    networkCall: suspend () -> Resource<T>,
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }


