package com.petapp

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit


private const val IMAGES_HTTP_CLIENT_CACHE = 100L * 1024L * 1024L

@GlideModule
class GlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val imagesOkHttpClient: OkHttpClient = getImagesOkHttpClient(context)
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(imagesOkHttpClient))
    }


    private fun getImagesOkHttpClient(context: Context): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, IMAGES_HTTP_CLIENT_CACHE))
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.addNetworkInterceptor(Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            originalResponse.newBuilder().header("Cache-Control", "max-age=" + 60 * 60 * 24).build()
        })
        return clientBuilder.build()
    }
}
