package edu.iesam.pokemon.feature.presentation

import android.content.Context
import edu.iesam.pokemon.feature.data.PokemonDataRepository
import edu.iesam.pokemon.feature.data.local.PokemonXmlLocalDataSource
import edu.iesam.pokemon.feature.data.remote.PokemonMockRemoteDataSource
import edu.iesam.pokemon.feature.domain.GetPokemonUseCase
import edu.iesam.pokemon.feature.domain.GetPokemonsUseCase

class PokemonFactory(private val context : Context) {

    private val pokemonMockRemote = PokemonMockRemoteDataSource()
    private val pokemonLocal = PokemonXmlLocalDataSource(context)
    private val pokemonDataRepository = PokemonDataRepository(pokemonLocal, pokemonMockRemote)
    private val getPokemonUseCase = GetPokemonUseCase(pokemonDataRepository)
    private val getPokemonsUseCase = GetPokemonsUseCase(pokemonDataRepository)

    fun buildViewModel(): PokemonViewModel {
        return PokemonViewModel(getPokemonsUseCase)
    }


    fun buildDetailViewModel(): PokemonDetailViewModel {
        return PokemonDetailViewModel(getPokemonUseCase)
    }

}