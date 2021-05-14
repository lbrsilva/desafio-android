package br.com.lbrsilva.nasa.data.datasource

import br.com.lbrsilva.nasa.data.adapter.Resource
import br.com.lbrsilva.nasa.data.model.Media

interface CarouselDataSource {
    suspend fun medias(startDate: String, endDate: String): Resource<List<Media>>
}