package edu.iesam.pokemon.feature.domain

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(PokemonId: String): Pokemon? {
        return pokemonRepository.getPokemon(PokemonId)
    }
}