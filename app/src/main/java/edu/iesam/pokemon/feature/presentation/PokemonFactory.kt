package edu.iesam.pokemon.feature.presentation

import android.content.Context
import edu.iesam.pokemon.app.data.api.ApiClient
import edu.iesam.pokemon.feature.data.PokemonDataRepository
import edu.iesam.pokemon.feature.data.local.PokemonXmlLocalDataSource
import edu.iesam.pokemon.feature.data.remote.PokemonApiRemoteDataSource
import edu.iesam.pokemon.feature.domain.GetPokemonUseCase
import edu.iesam.pokemon.feature.domain.GetPokemonsUseCase
import edu.iesam.pokemon.feature.domain.PokemonRepository

class PokemonFactory(private val context: Context) {

    private val pokemonLocal = PokemonXmlLocalDataSource(context)
    private val pokemonApiRemote = getPokemonApiRemoteDataSource()

    private val pokemonDataRepository: PokemonDataRepository = PokemonDataRepository(pokemonLocal, pokemonApiRemote)
    private val getPokemonUseCase = GetPokemonUseCase(pokemonDataRepository)
    private val getPokemonsUseCase = GetPokemonsUseCase(pokemonDataRepository)

    fun buildViewModel(): PokemonViewModel {
        return PokemonViewModel(getPokemonsUseCase)
    }

    fun buildDetailViewModel(): PokemonDetailViewModel {
        return PokemonDetailViewModel(getPokemonUseCase)
    }

    private fun getPokemonApiRemoteDataSource(): PokemonApiRemoteDataSource {
        val pokemonService = ApiClient.providePokemonService()
        return PokemonApiRemoteDataSource(pokemonService)
    }
}


