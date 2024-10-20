package edu.iesam.pokemon.features.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import edu.iesam.pokemon.databinding.FragmentPokemonBinding
import edu.iesam.pokemon.app.domain.ErrorApp
import edu.iesam.pokemon.features.domain.Pokemon

class PokemonFragment : Fragment() {

    private lateinit var pokemonFactory: PokemonFactory
    private lateinit var viewModel: PokemonViewModel

    private val pokemonAdapter = PokemonAdapter()

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonFactory = PokemonFactory(requireContext())
        viewModel = pokemonFactory.buildViewModel()
        setupObserver()
        viewModel.viewCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.apply {
            list.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            list.adapter = pokemonAdapter
        }
    }

    private fun setupObserver() {
        val pokemonObserver = Observer<PokemonViewModel.UiState> { state ->
            state.pokemon?.let {
                bindData(it)
            }
            state.errorApp?.let {
                showError(it)
            }
            if (state.isLoading) {
                Log.d("@dev", "Cargando...")
            } else {
                Log.d("@dev", "Cargado")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, pokemonObserver)
    }

    private fun bindData(pokemonList: List<Pokemon>) {
        pokemonAdapter.submitList(pokemonList)
    }

    private fun showError(error: ErrorApp) {
        when (error) {
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknowErrorApp -> TODO()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

