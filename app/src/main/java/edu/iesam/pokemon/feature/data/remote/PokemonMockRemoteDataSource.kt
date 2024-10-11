package edu.iesam.pokemon.feature.data.remote

import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonMockRemoteDataSource {
    private val pokemonList = listOf(
        Pokemon("1", "Bulbasaur", "Grass", 45, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/001.png"),
        Pokemon("2", "Ivysaur", "Grass", 60, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/002.png"),
        Pokemon("3", "Venusaur", "Grass", 80, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/003.png"),
    )

    fun getAllPokemon(): List<Pokemon> {
        return pokemonList
    }

    fun getPokemon(pokemonId: String): Pokemon?{
        return pokemonList.firstOrNull{ pokemon ->
            pokemon.id == pokemonId
        }
    }
}