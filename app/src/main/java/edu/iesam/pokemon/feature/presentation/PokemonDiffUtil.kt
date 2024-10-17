package edu.iesam.pokemon.feature.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonDiffUtil: DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }


}
