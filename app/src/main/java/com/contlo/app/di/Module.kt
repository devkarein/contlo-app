package com.contlo.app.di

import com.contlo.app.data.source.PullReqDataSource
import com.contlo.app.data.source.PullReqDataSourceImpl
import com.contlo.app.data.source.PullRequestsRepository
import com.contlo.app.data.source.PullRequestsRepositoryImpl
import com.contlo.app.data.source.networking.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Hilt dependency module to provide dependency
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    val BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun providesDatsSource(dataSource: PullReqDataSourceImpl): PullReqDataSource

    @Singleton
    @Binds
    abstract fun providesRepository(repo: PullRequestsRepositoryImpl): PullRequestsRepository

}