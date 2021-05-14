package br.com.lbrsilva.nasa.data.repository

import br.com.lbrsilva.nasa.data.datasource.GalleryDataSource
import javax.inject.Inject

open class GalleryRepositoryImpl @Inject constructor(
    private val dataSource: GalleryDataSource
) : GalleryRepository {
    override suspend fun medias(startDate: String, endDate: String) =
        this.dataSource.medias(startDate, endDate)
}