package edu.iesam.pokemon.feature.data.remote

import edu.iesam.pokemon.feature.domain.Pokemon
import edu.iesam.pokemon.feature.domain.PokemonResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun fetchPokemons(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id")id: String): Pokemon

}