package com.devtamuno.composeexerecise.remote.di

import com.devtamuno.composeexerecise.remote.repository.ProductsRemoteRepository
import com.devtamuno.composeexerecise.remote.repository.ProductsRemoteRepositoryImpl
import com.devtamuno.composeexerecise.remote.service.ProductService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Module
@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    private val config = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @[Provides Singleton]
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(config.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)

        return okHttpBuilder.build()
    }

    @[Provides Singleton]
    fun providesProductService(client: Retrofit): ProductService {
        return client.create(ProductService::class.java)
    }

    @[Provides Singleton]
    fun providesProductRemoteRepository(service: ProductService): ProductsRemoteRepository {
        return ProductsRemoteRepositoryImpl(service)
    }

}