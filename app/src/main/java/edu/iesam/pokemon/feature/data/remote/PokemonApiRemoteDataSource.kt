package edu.iesam.pokemon.feature.data.remote

import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonApiRemoteDataSource(private val pokemonService: PokemonService) {

    suspend fun fetchPokemons(): List<Pokemon> {
        return pokemonService.fetchPokemons().results
    }

    suspend fun fetchPokemonById(id: String): Pokemon {
        return pokemonService.getPokemon(id)
    }
}