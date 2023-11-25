package com.yandexpracticum.movie.ui.poster

import android.app.Activity
import android.os.Bundle
import com.yandexpracticum.movie.R
import com.yandexpracticum.movie.Creator

class PosterActivity : Activity() {

    private val posterController = Creator.providePosterController(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}