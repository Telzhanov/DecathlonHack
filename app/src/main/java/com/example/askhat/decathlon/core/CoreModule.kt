package com.example.askhat.decathlon.core

import com.example.askhat.decathlon.core.util.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val coreModule = module {
    single { createOkHttpClient() }
}

/**
 * Creates singleton okHttp client
 */
fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.api(message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

/**
 * Creates AuthService from okHttp client
 */
inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}