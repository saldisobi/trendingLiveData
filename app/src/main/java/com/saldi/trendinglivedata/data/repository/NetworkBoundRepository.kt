package com.saldi.trendinglivedata.data.repository

import androidx.annotation.MainThread
import com.saldi.trendinglivedata.data.model.ApiResponse
import com.saldi.trendinglivedata.data.network.NetworkUtils
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<ApiResponse<RESULT>> {
        try {
            emit(ApiResponse.loading())


            val apiResponse = fetchFromRemote()

            val remoteResponse = apiResponse.body()

            if (apiResponse.isSuccessful && remoteResponse != null) {

                emit(ApiResponse.success(remoteResponse as RESULT))


            } else {
                // Something went wrong! Emit Error state.
                emit(ApiResponse.error(apiResponse.message()))
            }


        } catch (exception: Exception) {
            emit(ApiResponse.error(NetworkUtils.ERROR_MESSAGE))
            exception.printStackTrace()
        }


    }

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>

}