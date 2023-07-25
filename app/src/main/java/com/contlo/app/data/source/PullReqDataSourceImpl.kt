package com.contlo.app.data.source

import com.contlo.app.data.model.ApiResponse
import com.contlo.app.data.model.PrApiModel
import com.contlo.app.data.source.networking.ApiService
import retrofit2.HttpException
import javax.inject.Inject

class PullReqDataSourceImpl @Inject constructor(private val service: ApiService) : PullReqDataSource {
    override suspend fun fetchAll(): ApiResponse<List<PrApiModel>> {
        return try {
            val response = service.loadPRs()
            if (response.isSuccessful && response.body() != null) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Failure(response.code(), response.message())
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            ApiResponse.Failure(e.code(), e.message())
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse.Exception(e)
        }
    }
}