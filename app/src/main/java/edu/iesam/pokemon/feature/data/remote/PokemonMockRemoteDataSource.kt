package edu.iesam.pokemon.feature.data.remote

import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonMockRemoteDataSource {
    private val pokemonList = listOf(
        Pokemon("1", "Bulbasaur", "Grass", 45, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/001.png"),
        Pokemon("2", "Ivysaur", "Grass", 60, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/002.png"),
        Pokemon("3", "Venusaur", "Grass", 80, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/003.png"),
        Pokemon("4", "Charmander", "Fire", 39, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/004.png"),
        Pokemon("5", "Charmeleon", "Fire", 58, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/005.png"),
        Pokemon("6", "Charizard", "Fire/Flying", 78, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/006.png"),
        Pokemon("7", "Squirtle", "Water", 44, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/007.png"),
        Pokemon("8", "Wartortle", "Water", 59, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/008.png"),
        Pokemon("9", "Blastoise", "Water", 79, "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/009.png")
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