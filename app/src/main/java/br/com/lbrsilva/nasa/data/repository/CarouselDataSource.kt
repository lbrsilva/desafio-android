package br.com.lbrsilva.nasa.data.repository

import br.com.lbrsilva.nasa.data.model.Picture
import br.com.lbrsilva.nasa.data.model.Resource

interface CarouselDataSource {
    suspend fun pictures(startDate: String, endDate: String): Resource<List<Picture>>
}