package edu.iesam.pokemon.feature.presentation

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
import edu.iesam.pokemon.app.extension.loadUrl
import edu.iesam.pokemon.databinding.FragmentPokemonDetailBinding
import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonDetailFragment: Fragment() {

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel : PokemonDetailViewModel

   private val pokemonArgs: PokemonDetailFragmentArgs by navArgs()

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
        viewModel = pokemonFactory.buildDetailViewModel()
        setupObserver()
        getPokemonId()?.let { viewModel.viewCreated(it) }
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            uiState.pokemon?.let { bindData(it) }
            uiState.errorApp?.let { showError(it) }
            if (uiState.isLoading) {
                Log.d("@dev", "Cargando...")
                // Mostrar progress bar
            } else {
                Log.d("@dev", "Cargado ...")
                // Ocultar progress bar
            }
        }
    }

    fun bindData(pokemon: Pokemon) {
        binding.pokemonImageView.loadUrl(pokemon.url)
        Log.d("@dev", "Url mostrada: ${pokemon.url}")
        binding.pokemonNameTextView.text = pokemon.name
    }

    private fun showError(error: ErrorApp) {
        when (error) {
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknowErrorApp -> TODO()
        }
    }

    private fun getPokemonId(): String?{
       return pokemonArgs.pokemonId
    }


    companion object{
        const val KEY_POKEMON_ID = "key_pokemon_id"
        fun getIntent(context: Context, superheroId: String) = Intent(context, PokemonDetailActivty::class.java).apply {
            putExtra(KEY_POKEMON_ID, superheroId)
        }
    }


}