package br.com.lbrsilva.nasa.data.repository

import br.com.lbrsilva.nasa.data.datasource.CarouselDataSource
import javax.inject.Inject

open class CarouselRepositoryImpl @Inject constructor(
    private val dataSource: CarouselDataSource
) : CarouselRepository {
    override suspend fun medias(startDate: String, endDate: String) =
        this.dataSource.medias(startDate, endDate)
}