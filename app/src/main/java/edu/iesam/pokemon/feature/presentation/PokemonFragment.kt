package edu.iesam.pokemon.feature.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.pokemon.R
import edu.iesam.pokemon.app.domain.ErrorApp
import edu.iesam.pokemon.databinding.FragmentPokemonBinding
import edu.iesam.pokemon.feature.domain.Pokemon

class PokemonFragment : Fragment() {

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel: PokemonViewModel

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    private val pokemonAdapter = PokemonAdapter()

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
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

    }

    private fun setupObserver() {
        val pokemonObserver = Observer<PokemonViewModel.UiState> { uiState ->
            uiState.pokemon?.let {
                pokemonAdapter.submitList(it)
            }
            uiState.errorApp?.let {
                showError(it)
            }
            Log.d("@dev", if (uiState.isLoading) "Cargando..." else "Cargado ...")
        }
        viewModel.uiState.observe(viewLifecycleOwner, pokemonObserver)
    }

    private fun setupView() {
        binding.apply {
            recyclerViewPokemon.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerViewPokemon.adapter = pokemonAdapter
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
