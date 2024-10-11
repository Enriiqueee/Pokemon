package edu.iesam.pokemon.feature.data

import edu.iesam.pokemon.feature.data.local.PokemonXmlLocalDataSource
import edu.iesam.pokemon.feature.data.remote.PokemonMockRemoteDataSource
import edu.iesam.pokemon.feature.domain.Pokemon
import edu.iesam.pokemon.feature.domain.PokemonRepository

class PokemonDataRepository(private val local: PokemonXmlLocalDataSource, private val mockRemoteDataSource: PokemonMockRemoteDataSource): PokemonRepository {

    override fun getAllPokemon(): List<Pokemon> {
        val pokemonFromLocal = local.findAll()
        if (pokemonFromLocal.isEmpty()) {
            val pokemonFromRemote = mockRemoteDataSource.getAllPokemon()
            local.saveAll(pokemonFromRemote)
            return pokemonFromRemote
        }else{
            return pokemonFromLocal
        }
    }

    override fun getPokemon(pokemonId: String): Pokemon? {
        val localPokemon = local.finById(pokemonId)
        if(localPokemon == null){
            mockRemoteDataSource.getPokemon(pokemonId)?.let { pokemon ->
                local.save(pokemon)
                return pokemon
            }
        }
        return localPokemon
    }

}