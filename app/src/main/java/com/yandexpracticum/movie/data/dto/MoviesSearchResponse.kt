package com.yandexpracticum.movie.data.dto

import com.yandexpracticum.movie.domain.models.Movie

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<Movie>)