package br.com.lbrsilva.nasa.data.repository

import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.data.model.Resource

interface CarouselRepository {
    suspend fun medias(startDate: String, endDate: String): Resource<List<Media>>
}