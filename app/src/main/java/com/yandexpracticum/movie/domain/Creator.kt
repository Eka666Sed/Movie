package com.yandexpracticum.movie.domain

import com.yandexpracticum.movie.data.MoviesRepositoryImpl
import com.yandexpracticum.movie.data.network.RetrofitNetworkClient
import com.yandexpracticum.movie.domain.api.MoviesInteractor
import com.yandexpracticum.movie.domain.api.MoviesRepository
import com.yandexpracticum.movie.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}