package com.example.chiller.network

import com.example.chiller.BuildConfig
import com.example.chiller.common.AppConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        private val okHttpClient = if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(RequestInterceptor())
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())
                .build()
        }

        private val retrofitClient = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_RES_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: ApiService by lazy {
            retrofitClient.create(ApiService::class.java)
        }

    }
}