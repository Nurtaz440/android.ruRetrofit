package mening.dasturim.androidruretrofit.retrofit

import mening.dasturim.androidruretrofit.data.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object MoviApiClient {



    val apiClient: MoviesApiInterface by lazy {

        val logging=HttpLoggingInterceptor()
        logging.level=HttpLoggingInterceptor.Level.BODY

        val okHttps=OkHttpClient.Builder()
            .readTimeout(100,TimeUnit.SECONDS)
            .connectTimeout(100,TimeUnit.SECONDS)
            .addInterceptor { chain->
                val request=chain.request().newBuilder().apply {
                    addHeader("Content-Type","application/json")
                }.build()
                chain.proceed(request)
            }.addInterceptor(logging)
            .build()




        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttps)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return@lazy retrofit.create(MoviesApiInterface::class.java)
    }
}