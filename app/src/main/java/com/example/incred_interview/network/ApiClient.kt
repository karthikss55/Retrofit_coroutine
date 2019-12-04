package com.example.incred_interview.network

import com.example.incred_interview.ui.MainApplication
import com.example.incred_interview.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class ApiClient() {
    companion object {
        private var retrofit: Retrofit? = null
        // Create a cache object
        val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
        val cache = Cache(MainApplication.getContext().cacheDir, cacheSize)


        private const val BASE_URL: String = "https://api.flickr.com/services/rest/"
        fun getInstance(): RestService? {
            retrofit = retrofit ?: buildRetrofit()

            return retrofit?.create(RestService::class.java)

        }


        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (NetworkUtils.isNetworkConnected(MainApplication.getContext()))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()

//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(ConnectivityInterceptor( MainApplication.getContext()))
//            .addNetworkInterceptor(CacheInterceptor())
//            .build()

        private fun buildRetrofit() = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    }
}