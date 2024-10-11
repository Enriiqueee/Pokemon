package edu.iesam.pokemon.feature.domain

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    fun execute(PokemonId: Int): Pokemon {
        return pokemonRepository.getPokemon(PokemonId)
    }
}