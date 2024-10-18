package edu.iesam.pokemon.feature.data

import edu.iesam.pokemon.feature.data.local.PokemonXmlLocalDataSource
import edu.iesam.pokemon.feature.data.remote.PokemonApiRemoteDataSource
import edu.iesam.pokemon.feature.domain.Pokemon
import edu.iesam.pokemon.feature.domain.PokemonRepository

class PokemonDataRepository(
    private val localDataSource: PokemonXmlLocalDataSource,
    private val remoteDataSource: PokemonApiRemoteDataSource
): PokemonRepository {

    override suspend fun getAllPokemon(): List<Pokemon> {
        return remoteDataSource.fetchPokemons()
    }

    override suspend fun getPokemon(id: String): Pokemon? {
        return remoteDataSource.fetchPokemonById(id)
    }
}
