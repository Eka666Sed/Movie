package com.yandexpracticum.movie.util

import android.app.Activity
import android.content.Context
import com.yandexpracticum.movie.data.MoviesRepositoryImpl
import com.yandexpracticum.movie.data.network.RetrofitNetworkClient
import com.yandexpracticum.movie.domain.api.MoviesInteractor
import com.yandexpracticum.movie.domain.api.MoviesRepository
import com.yandexpracticum.movie.domain.impl.MoviesInteractorImpl
import com.yandexpracticum.movie.presentation.movies.MoviesSearchPresenter
import com.yandexpracticum.movie.presentation.PosterController
import com.yandexpracticum.movie.presentation.movies.MoviesView
import com.yandexpracticum.movie.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(view = moviesView, context = context)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}