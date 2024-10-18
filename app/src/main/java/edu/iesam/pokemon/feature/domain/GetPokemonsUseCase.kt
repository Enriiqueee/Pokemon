package edu.iesam.pokemon.feature.domain

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): List<Pokemon> {
        return pokemonRepository.getAllPokemon()
    }
}
