package com.petapp.details.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.petapp.R
import com.petapp.databinding.FragmentPetDetailsBinding
import com.petapp.details.model.PetDetailsViewModel
import com.petapp.util.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetDetailsFragment : Fragment(R.layout.fragment_pet_details) {
    private val binding by viewBinding(FragmentPetDetailsBinding::bind)
    private val viewModel: PetDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initObservers()
    }

    private fun initObservers() {
        viewModel.navigateBack.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
        viewModel.showErrorToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.failed_to_load_pet_details, Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
    }
}