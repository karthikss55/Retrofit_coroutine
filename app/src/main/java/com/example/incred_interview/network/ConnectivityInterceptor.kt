package com.example.incred_interview.network

import android.content.Context
import com.example.incred_interview.utils.NetworkUtils
import com.example.incred_interview.utils.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!NetworkUtils.isNetworkConnected(context)){
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}