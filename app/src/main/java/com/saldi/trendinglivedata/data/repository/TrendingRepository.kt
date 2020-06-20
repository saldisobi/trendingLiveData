package com.saldi.trendinglivedata.data.repository

import com.saldi.trendinglivedata.data.model.ApiResponse
import com.saldi.trendinglivedata.data.model.TrendingListItem
import com.saldi.trendinglivedata.data.network.GitHubService
import com.saldi.trendinglivedata.data.network.NetworkUtils
import com.saldi.trendinglivedata.data.persist.TinyDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

open class TrendingRepository @Inject constructor(
    private val gitHubService: GitHubService,
    private val tintDb: TinyDB
) {

  open  fun getTrending(
        language: String,
        since: String,
        spokenLanguage: String,
        isForce: Boolean
    ): Flow<ApiResponse<List<TrendingListItem>>> {
        return object : NetworkBoundRepository<List<TrendingListItem>, List<TrendingListItem>>() {


            override suspend fun fetchFromRemote(): Response<List<TrendingListItem>> =
                gitHubService.getTrendingRepositories(language, since, spokenLanguage)



        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun isUpdateRequired(): Boolean {
        return NetworkUtils.isUpdateRequired(tintDb.getLong(NetworkUtils.PREVIOUS_SYNC_TIME, 0))
    }
}