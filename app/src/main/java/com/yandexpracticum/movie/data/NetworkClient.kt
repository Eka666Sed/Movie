package com.yandexpracticum.movie.data

import com.yandexpracticum.movie.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any) : Response
}