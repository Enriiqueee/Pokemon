package edu.iesam.pokemon.feature.domain

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepository) {
    fun execute(): List<Pokemon> {
        return pokemonRepository.getAllPokemon()
    }
}
