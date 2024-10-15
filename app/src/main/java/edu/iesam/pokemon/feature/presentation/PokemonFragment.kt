package edu.iesam.pokemon.feature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.pokemon.R
import edu.iesam.pokemon.app.domain.ErrorApp
import edu.iesam.pokemon.databinding.FragmentPokemonBinding
import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonFragment : Fragment() {

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel: PokemonViewModel

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonFactory = PokemonFactory(requireContext())
        viewModel = pokemonFactory.buildViewModel()
        viewModel.viewCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

    }

    private fun setupObserver() {
        val pokemonObserver = Observer<PokemonViewModel.UiState> { uiState ->
            uiState.pokemon?.let {
                bindData(it)
            }
            uiState.errorApp?.let {
                showError(it)
            }
            Log.d("@dev", if (uiState.isLoading) "Cargando..." else "Cargado ...")
        }
        viewModel.uiState.observe(viewLifecycleOwner, pokemonObserver)
    }

    private fun bindData(pokemon: List<Pokemon>) {
        binding.apply {
            pokemonId1.text = pokemon[0].id
            pokemonName1.text = pokemon[0].name
            pokemonName1.setOnClickListener {
                navigateToPokemonDetail(pokemon[0].id)
            }

            pokemonId2.text = pokemon[1].id
            pokemonName2.text = pokemon[1].name
            pokemonName2.setOnClickListener {
                navigateToPokemonDetail(pokemon[1].id)
            }

            pokemonId3.text = pokemon[2].id
            pokemonName3.text = pokemon[2].name
            pokemonName3.setOnClickListener {
                navigateToPokemonDetail(pokemon[2].id)
            }
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

    private fun navigateToPokemonDetail(pokemonId: String) {
        findNavController().navigate(
            PokemonFragmentDirections.actionPokemonFragmentToPokemonDetailFragment(pokemonId)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
