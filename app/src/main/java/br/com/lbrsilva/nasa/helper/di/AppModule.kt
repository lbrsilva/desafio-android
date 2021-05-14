package br.com.lbrsilva.nasa.helper.di

import br.com.lbrsilva.nasa.data.datasource.GalleryDataSource
import br.com.lbrsilva.nasa.data.datasource.GalleryDataSourceImpl
import br.com.lbrsilva.nasa.data.repository.GalleryRepository
import br.com.lbrsilva.nasa.data.repository.GalleryRepositoryImpl
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
    fun provideGalleryRepository(repository: GalleryRepositoryImpl): GalleryRepository = repository

    @Provides
    @Singleton
    fun provideGalleryDataSource(dataSource: GalleryDataSourceImpl): GalleryDataSource = dataSource
}