package com.yandexpracticum.movie.data.dto

class MoviesSearchResponse(val searchType: String,
                            val expression: String,
                            val results: List<MovieDto>) : Response()