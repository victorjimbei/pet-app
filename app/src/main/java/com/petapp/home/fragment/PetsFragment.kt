package com.petapp.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.petapp.R
import com.petapp.databinding.FragmentPetsBinding
import com.petapp.home.adapter.PetsAdapter
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.model.PetUi
import com.petapp.home.model.PetsViewModel
import com.petapp.util.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PetsFragment : Fragment(R.layout.fragment_pets), OnPetClickListener {
    private val binding by viewBinding(FragmentPetsBinding::bind)

    @Inject
    lateinit var petsViewModel: PetsViewModel

    private val petsAdapter = PetsAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = petsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvPets.adapter = petsAdapter
        initObservers()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.swipeRefresh.setOnRefreshListener { petsAdapter.refresh() }
    }

    private fun initObservers() {
        petsViewModel.pets.observe(viewLifecycleOwner) {
            petsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onPetClicked(petUi: PetUi) {
        findNavController().navigate(PetsFragmentDirections.toPetDetailsFragment(petUi.id))
    }
}