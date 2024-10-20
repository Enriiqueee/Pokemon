package edu.iesam.pokemon.features.presentation

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.pokemon.app.extensions.loadUrl
import edu.iesam.pokemon.databinding.ViewPokemonItemBinding
import edu.iesam.pokemon.features.domain.Pokemon

class PokemonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    private val binding: ViewPokemonItemBinding = ViewPokemonItemBinding.bind(view)

    fun bind(model: Pokemon) {
        binding.apply {
            pokemonImage.loadUrl(model.url)
            pokemonNombre.text = model.name
           pokemonNombre.setOnClickListener {
                navigateToDetails(model.id)
            }
        }
    }

    private fun navigateToDetails(pokemonId: String) {
        findNavController(view).navigate(
            PokemonFragmentDirections.actionFragmentPokemonToFragmentPokemonDetail(pokemonId)
        )
    }
}
