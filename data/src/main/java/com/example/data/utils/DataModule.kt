package com.example.data.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor()= HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun providesHttpLoggingClient()= OkHttpClient.Builder()
        .addInterceptor(providesHttpLoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .build()


    @Provides
    @Singleton
    fun proviesRetrofit()= Retrofit.
    Builder().
    baseUrl(Constants.BASE_URL).
    client(providesHttpLoggingClient()).
    addConverterFactory(GsonConverterFactory.create()).
    build()



}