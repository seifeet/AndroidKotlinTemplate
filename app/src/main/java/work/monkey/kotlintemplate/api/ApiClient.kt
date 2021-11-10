package work.monkey.kotlintemplate.api

import android.os.Build
import work.monkey.kotlintemplate.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader(Constant.AUTH_TOKEN, Build.ID)
            .build()

        chain.proceed(request)
    }

    private val helper = MtlsHelper(trustAll = true)

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = when {
            BuildConfig.DEBUG -> {
                HttpLoggingInterceptor.Level.BODY
            }
            else -> {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    // OkhttpClient is used for building http request url
    private val client = OkHttpClient().newBuilder()
        .sslSocketFactory(helper.sslSocketFactory, helper.trustManager)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}