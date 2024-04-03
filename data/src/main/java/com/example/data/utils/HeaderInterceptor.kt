package com.example.data.utils

import com.example.data.utils.Constants.BEARER_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", BEARER_TOKEN)
                .build()
        )
    }
}