package com.contlo.app.data.source.networking

import com.contlo.app.data.model.PrApiModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("repos/square/retrofit/pulls?state=closed")
    suspend fun loadPRs(): Response<List<PrApiModel>>
}