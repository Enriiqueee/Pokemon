package edu.iesam.pokemon.feature.domain

interface PokemonRepository {
    fun getAllPokemon(): List<Pokemon>
    fun getPokemon(pokemonId: String): Pokemon?

}