package com.yandexpracticum.movie

import android.app.Activity
import com.yandexpracticum.movie.data.MoviesRepositoryImpl
import com.yandexpracticum.movie.data.network.RetrofitNetworkClient
import com.yandexpracticum.movie.domain.api.MoviesInteractor
import com.yandexpracticum.movie.domain.api.MoviesRepository
import com.yandexpracticum.movie.domain.impl.MoviesInteractorImpl
import com.yandexpracticum.movie.presentation.MoviesSearchController
import com.yandexpracticum.movie.presentation.PosterController
import com.yandexpracticum.movie.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}