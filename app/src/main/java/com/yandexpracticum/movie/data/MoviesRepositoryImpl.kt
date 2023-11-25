package com.yandexpracticum.movie.data

import com.yandexpracticum.movie.data.dto.MoviesSearchRequest
import com.yandexpracticum.movie.data.dto.MoviesSearchResponse
import com.yandexpracticum.movie.domain.api.MoviesRepository
import com.yandexpracticum.movie.domain.models.Movie
import com.yandexpracticum.movie.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)
                })
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}