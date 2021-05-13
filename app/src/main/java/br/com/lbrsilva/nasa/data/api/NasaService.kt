package br.com.lbrsilva.nasa.data.api

import br.com.lbrsilva.nasa.BuildConfig
import br.com.lbrsilva.nasa.data.model.Media
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET("planetary/apod")
    suspend fun medias(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<List<Media>>
}