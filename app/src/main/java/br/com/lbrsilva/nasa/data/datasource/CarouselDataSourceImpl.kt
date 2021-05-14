package br.com.lbrsilva.nasa.data.datasource

import br.com.lbrsilva.nasa.data.adapter.Resource
import br.com.lbrsilva.nasa.data.api.NasaService
import br.com.lbrsilva.nasa.data.model.Media
import javax.inject.Inject

class CarouselDataSourceImpl @Inject constructor(
    private val service: NasaService
) : CarouselDataSource {
    override suspend fun medias(startDate: String, endDate: String): Resource<List<Media>> {
        try {
            val response = service.medias(startDate, endDate)

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