package com.tei.harrypottercharacter.data.network

import com.tei.harrypottercharacter.data.local.entities.CharacterEntity
import retrofit2.http.GET

interface APIService {

    @GET("characters")
    suspend fun fetchCharactersList() : List<CharacterEntity>
}