package edu.iesam.pokemon.feature.presentation

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.pokemon.app.extensions.loadUrl
import edu.iesam.pokemon.databinding.ViewPokemonItemBinding
import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonViewHolder(val view : View) : RecyclerView.ViewHolder(view){

    private val binding: ViewPokemonItemBinding = ViewPokemonItemBinding.bind(view)

    fun bind(model: Pokemon){
        binding.apply {
            image.loadUrl(model.url)
            id.text = model.id
            name.text = model.name
            pokemonViewLayout.setOnClickListener {
                navigateToPokemonDetail(model.id)
            }
        }
    }

    private fun navigateToPokemonDetail(pokemonId: String) {
        findNavController(view).navigate(
            PokemonFragmentDirections.actionPokemonFragmentToPokemonDetailFragment(pokemonId)
        )
    }

}