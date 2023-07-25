package com.contlo.app.data.source

import com.contlo.app.data.model.ApiResponse
import com.contlo.app.data.model.PrApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PullRequestsRepositoryImpl @Inject constructor(val dataSource: PullReqDataSource) :
    PullRequestsRepository {

    override suspend fun loadAllPRs(): ApiResponse<List<PrApiModel>> =
        withContext(Dispatchers.IO) {
            dataSource.fetchAll()
        }

}