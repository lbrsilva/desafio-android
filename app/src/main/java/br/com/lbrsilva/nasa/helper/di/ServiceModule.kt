package br.com.lbrsilva.nasa.helper.di

import br.com.lbrsilva.nasa.BuildConfig
import br.com.lbrsilva.nasa.data.api.CacheInterceptor
import br.com.lbrsilva.nasa.data.api.NasaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    @Singleton
    fun provideNasaService(): NasaService {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(Level.BODY)
            httpClient.addInterceptor(logging)
        }

        httpClient.addNetworkInterceptor(CacheInterceptor())
        builder.client(httpClient.build())

        return builder.build()
            .create(NasaService::class.java)
    }
}