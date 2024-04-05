package com.example.data.utils

import com.example.common.utils.Constants
import com.example.data.network.TMDBAPI
import com.example.data.repository.RemoteRepositoryImpl
import com.example.data.usecase.DetailsUseCaseImpl
import com.example.data.usecase.HomeUseCaseImpl
import com.example.data.usecase.SearchUseCaseImpl
import com.example.domain.repository.RemoteRepository
import com.example.domain.usecase.DetailsUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.dnsoverhttps.DnsOverHttps
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor()= HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)


    val appCache = Cache(File("cacheDir", "okhttpcache"), 10 * 1024 * 1024)
    val bootstrapClient = OkHttpClient.Builder().cache(appCache).build()

    val dns = DnsOverHttps.Builder().client(bootstrapClient)
        .url("https://dns.google/dns-query".toHttpUrl())
        .bootstrapDnsHosts(InetAddress.getByName("8.8.4.4"), InetAddress.getByName("8.8.8.8"))
        .build()



    @Singleton
    @Provides
    fun providesHttpClient()= OkHttpClient.Builder()
        .addInterceptor(providesHttpLoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .dns(dns)
        .build()


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.
    Builder().
    baseUrl(Constants.BASE_URL).
    client(providesHttpClient()).
    addConverterFactory(GsonConverterFactory.create()).
    build()

    @Provides
    @Singleton
    fun providesTmdbAPI():TMDBAPI= providesRetrofit().create(TMDBAPI::class.java)

    @Provides
    @Singleton
    fun providesRemoteRepository():RemoteRepository = RemoteRepositoryImpl(providesTmdbAPI())

    @Provides
    @Singleton
    fun providesHomeUseCase():HomeUseCase = HomeUseCaseImpl(providesRemoteRepository())
    @Provides
    @Singleton
    fun providesDetailsUseCase():DetailsUseCase = DetailsUseCaseImpl(providesRemoteRepository())

    @Provides
    @Singleton
    fun providesSearchUseCase():SearchUseCase = SearchUseCaseImpl(providesRemoteRepository())



}