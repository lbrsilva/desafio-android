package br.com.lbrsilva.nasa.helper.di

import br.com.lbrsilva.nasa.data.repository.CarouselDataSource
import br.com.lbrsilva.nasa.data.repository.CarouselDataSourceImpl
import br.com.lbrsilva.nasa.data.repository.CarouselRepository
import br.com.lbrsilva.nasa.data.repository.CarouselRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCarouselRepository(repository: CarouselRepositoryImpl): CarouselRepository =
        repository

    @Provides
    @Singleton
    fun provideCarouselDataSource(dataSource: CarouselDataSourceImpl): CarouselDataSource =
        dataSource
}