package com.yandexpracticum.movie.domain.api

import com.yandexpracticum.movie.domain.models.Movie
import com.yandexpracticum.movie.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}