package edu.iesam.pokemon.features.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.iesam.pokemon.app.domain.ErrorApp
import edu.iesam.pokemon.app.extensions.loadUrl
import edu.iesam.pokemon.databinding.FragmentPokemonDetailBinding
import edu.iesam.pokemon.features.domain.Pokemon

class PokemonDetailFragment: Fragment() {

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel: PokemonDetailViewModel

    private val pokemonArgs : PokemonDetailFragmentArgs by navArgs()

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonFactory = PokemonFactory(requireContext())
        viewModel = pokemonFactory.buildPokemonDetailViewModel()

        setUpObserver()

        getPokemonId()?.let{
            viewModel.viewCreated(it)
        }
    }

    private fun setUpObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->

            uiState.pokemon?.let { bindData(it) }
            uiState.errorApp?.let { showError(it) }
            if (uiState.isLoading) {
                Log.d("@dev", "Cargando...")
            } else {
                Log.d("@dev", "Cargado ...")
            }
        }
    }

    private fun getPokemonId(): String? {
        return pokemonArgs.id
    }

    fun bindData(pokemon: Pokemon) {
        binding.apply {
            pokemonImage.loadUrl(pokemon.url)
            pokemonId.text = pokemon.id
            name.text = pokemon.name
            height.text = pokemon.height.toString()
            weight.text = pokemon.weight.toString()
        }
    }

    private fun showError(error: ErrorApp) {
        when (error) {
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknowErrorApp -> TODO()
        }
    }

    companion object {
        const val KEY_POKEMON_ID = "key_pokemon_id"
        fun getIntent(context: Context, pokemonId: String) = Intent(context, PokemonDetailActivity::class.java).apply {
            putExtra(KEY_POKEMON_ID, pokemonId)
        }

    }
}