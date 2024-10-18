package edu.iesam.pokemon.feature.domain

interface PokemonRepository {
    suspend fun getAllPokemon(): List<Pokemon>
    suspend fun getPokemon(pokemonId: String): Pokemon?

}