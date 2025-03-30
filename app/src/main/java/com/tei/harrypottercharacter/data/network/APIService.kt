package com.tei.harrypottercharacter.data.network

import retrofit2.http.GET

interface APIService {

    @GET("/characters")
    suspend fun fetchMovieCharacters()
}