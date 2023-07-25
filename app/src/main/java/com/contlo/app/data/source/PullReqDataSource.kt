package com.contlo.app.data.source

import com.contlo.app.data.model.ApiResponse
import com.contlo.app.data.model.PrApiModel

interface PullReqDataSource {
    suspend fun fetchAll(): ApiResponse<List<PrApiModel>>

}