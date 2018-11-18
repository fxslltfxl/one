package com.free.fxs.one.retrofit

import android.os.Environment
import com.free.fxs.one.constant.UrlConstant
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitHelper private constructor() {
    private var client: OkHttpClient
    var retrofit: Retrofit

    init {
        val file = File(Environment.getExternalStorageDirectory(), "cache")
        val cacheSize: Long = 10 * 1024 * 1024
        client = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .cache(Cache(file.absoluteFile, cacheSize))
            .build()
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(UrlConstant.LocalUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        private var instance: RetrofitHelper? = null
        @JvmStatic
        fun getInstance(): RetrofitHelper {
            if (null == instance) {
                synchronized(RetrofitHelper::class.java) {
                    if (null == instance) {
                        instance = RetrofitHelper()
                    }
                }
            }
            return instance!!
        }
    }

}