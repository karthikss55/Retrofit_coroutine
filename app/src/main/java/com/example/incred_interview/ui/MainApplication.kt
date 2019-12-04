package com.example.incred_interview.ui

import android.app.Application
import android.content.Context
import com.example.incred_interview.localDataBase.AppDatabase
import com.example.incred_interview.network.ApiClient
import com.example.incred_interview.network.RestService

class MainApplication : Application() {
    companion object {
        private lateinit var appContext: Context

        @JvmStatic
        fun getContext(): Context {
            return appContext
        }

         fun buildDatabase():AppDatabase {
            return AppDatabase.invoke(getContext())
        }

         fun getRestService(): RestService? {
            return ApiClient.getInstance()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

}