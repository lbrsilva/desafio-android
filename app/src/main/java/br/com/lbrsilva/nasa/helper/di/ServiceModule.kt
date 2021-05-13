package br.com.lbrsilva.nasa.helper.di

import br.com.lbrsilva.nasa.data.api.NasaService
import br.com.lbrsilva.nasa.helper.builder.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    @Singleton
    fun provideNasaService(): NasaService {
        return RetrofitBuilder.get().build().create(NasaService::class.java)
    }
}