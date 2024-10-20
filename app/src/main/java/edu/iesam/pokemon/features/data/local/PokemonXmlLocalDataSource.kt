package edu.iesam.pokemon.features.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.pokemon.features.domain.Pokemon

class PokemonXmlLocalDataSource(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        "pokemon", Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(pokemon: Pokemon) {
        val editor = sharedPref.edit()
        editor.putString(pokemon.id, gson.toJson(pokemon))
        editor.apply()
    }

    fun delete(){
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun saveAll(pokemons: List<Pokemon>) {
        val editor = sharedPref.edit()
        pokemons.forEach {
            editor.putString(it.id, gson.toJson(it))
        }
        editor.apply()
    }

    fun findById(id: String): Pokemon? {
        return sharedPref.getString(id, null).let {
            gson.fromJson(it, Pokemon::class.java)
        }
    }

    fun findAll(): List<Pokemon> {
        val pokemons = ArrayList<Pokemon>()
        val mapPokemon = sharedPref.all
        mapPokemon.forEach { jsonPokemon ->
            val pokemon = gson.fromJson(jsonPokemon.value as String, Pokemon::class.java)
            pokemons.add(pokemon)
        }
        return pokemons.sortedBy { it.id.toInt() }
    }

    fun deleteById(id: String) {
        val editor = sharedPref.edit()
        editor.remove(id)
        editor.apply()
    }
}