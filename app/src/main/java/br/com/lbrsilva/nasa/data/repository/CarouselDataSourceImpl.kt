package br.com.lbrsilva.nasa.data.repository

import br.com.lbrsilva.nasa.data.api.NasaService
import br.com.lbrsilva.nasa.data.model.Picture
import br.com.lbrsilva.nasa.data.model.Resource
import javax.inject.Inject

class CarouselDataSourceImpl @Inject constructor(
    private val service: NasaService
) : CarouselDataSource {
    override suspend fun pictures(startDate: String, endDate: String): Resource<List<Picture>> {
        try {
            val response = service.pictures(startDate, endDate)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return Resource.success(body)
                }
            }

            return Resource.error(code = response.code(), message = response.message())
        } catch (e: Exception) {
            return Resource.error(message = e.message ?: e.toString())
        }
    }
}