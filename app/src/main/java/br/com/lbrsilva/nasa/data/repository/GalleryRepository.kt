package br.com.lbrsilva.nasa.data.repository

import br.com.lbrsilva.nasa.data.adapter.Resource
import br.com.lbrsilva.nasa.data.model.Media

interface GalleryRepository {
    suspend fun medias(startDate: String, endDate: String): Resource<List<Media>>
}