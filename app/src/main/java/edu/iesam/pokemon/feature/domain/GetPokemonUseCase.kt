package edu.iesam.pokemon.feature.domain

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {
   suspend operator fun invoke(PokemonId: String): Pokemon? {
        return pokemonRepository.getPokemon(PokemonId)
    }
}