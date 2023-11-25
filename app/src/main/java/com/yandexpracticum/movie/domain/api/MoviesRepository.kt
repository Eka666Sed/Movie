package com.yandexpracticum.movie.domain.api

import com.yandexpracticum.movie.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}