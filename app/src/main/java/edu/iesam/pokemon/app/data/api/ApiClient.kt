package edu.iesam.pokemon.app.data.api

import edu.iesam.pokemon.feature.data.remote.PokemonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun providePokemonService(): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}