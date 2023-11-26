package com.yandexpracticum.movie.presentation.movies

import android.os.Handler
import android.app.Activity
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandexpracticum.movie.R
import androidx.recyclerview.widget.RecyclerView
import com.yandexpracticum.movie.util.Creator
import com.yandexpracticum.movie.domain.api.MoviesInteractor
import com.yandexpracticum.movie.domain.models.Movie
import com.yandexpracticum.movie.ui.movies.MoviesAdapter

class MoviesSearchPresenter(private val view: MoviesView,
                            private val adapter: MoviesAdapter
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(view)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable { searchRequest() }

    fun onCreate() {
        adapter.movies = movies
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce() {
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest() {
        if (queryInput.text.isNotEmpty()) {

            placeholderMessage.visibility = View.GONE
            moviesList.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            moviesInteractor.searchMovies(queryInput.text.toString(), object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        progressBar.visibility = View.GONE
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                            adapter.notifyDataSetChanged()
                            moviesList.visibility = View.VISIBLE
                        }
                        if (errorMessage != null) {
                            showMessage(activity.getString(R.string.something_went_wrong), errorMessage)
                        } else if (movies.isEmpty()) {
                            showMessage(activity.getString(R.string.nothing_found), "")
                        } else {
                            hideMessage()
                        }
                    }
                }
            })
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholderMessage.visibility = View.VISIBLE
            movies.clear()
            adapter.notifyDataSetChanged()
            placeholderMessage.text = text
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(activity, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            placeholderMessage.visibility = View.GONE
        }
    }

    private fun hideMessage() {
        placeholderMessage.visibility = View.GONE
    }
}