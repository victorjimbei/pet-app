package com.petapp.data.di

import android.util.Log
import com.petapp.data.remote.PetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {
    private val TAG = NetworkModule::class.java.simpleName
    val BASE_URL = "https://api.petfinder.com/"

    @Provides
    fun provideOtaApi(): PetsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(basicOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(PetsApi::class.java)


    val basicOkHttpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(getLogInterceptor())
            return clientBuilder.build()
        }

    private fun getLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.d(TAG, message) }.apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}