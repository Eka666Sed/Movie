package com.yandexpracticum.movie.ui.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandexpracticum.movie.ui.poster.PosterActivity
import com.yandexpracticum.movie.R
import com.yandexpracticum.movie.presentation.movies.MoviesView
import com.yandexpracticum.movie.util.Creator

class MoviesActivity : Activity(), MoviesView {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private val moviesSearchPresenter = Creator.provideMoviesSearchPresenter(this, adapter)

    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        placeholderMessage = activity.findViewById(R.id.placeholderMessage)
        queryInput = activity.findViewById(R.id.queryInput)
        moviesList = activity.findViewById(R.id.locations)
        progressBar = activity.findViewById(R.id.progressBar)

        moviesList.layoutManager = LinearLayoutManager(view, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter

        queryInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, after: Int) {
                moviesSearchPresenter.searchDebounce()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        moviesSearchPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesSearchPresenter.onDestroy()
    }


    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

}