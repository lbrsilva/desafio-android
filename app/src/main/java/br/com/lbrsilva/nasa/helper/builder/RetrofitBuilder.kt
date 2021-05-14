package br.com.lbrsilva.nasa.helper.builder

import android.util.Log
import br.com.lbrsilva.nasa.BuildConfig
import br.com.lbrsilva.nasa.NasaApplication
import br.com.lbrsilva.nasa.helper.util.Network
import okhttp3.*
import okhttp3.CacheControl.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    fun get(url: String = BuildConfig.BASE_URL): Retrofit.Builder {
        val builder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }

        httpClient.addInterceptor(provideOfflineCacheInterceptor())
        httpClient.addNetworkInterceptor(provideCacheInterceptor())
        httpClient.cache(provideCache())

        builder.client(httpClient.build())

        return builder
    }

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        val maxSize: Long = 10 * 1024 * 1024

        try {
            cache = Cache(File(NasaApplication.context().cacheDir, "http-cache"), maxSize)
        } catch (e: Exception) {
            Log.e("RETROFIT", "Could not create Cache!")
        }

        return cache
    }

    private fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl: CacheControl = Builder().maxAge(1, TimeUnit.DAYS).build()

            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            var request: Request = chain.request()
            if (!Network.isConnected()) {
                val cacheControl: CacheControl = Builder().maxStale(1, TimeUnit.DAYS).build()

                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }
}