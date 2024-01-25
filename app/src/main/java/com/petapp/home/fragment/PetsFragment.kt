package com.petapp.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.petapp.R
import com.petapp.databinding.FragmentPetsBinding
import com.petapp.home.model.PetsViewModel
import com.petapp.util.binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PetsFragment : Fragment(R.layout.fragment_pets) {
    private val binding by viewBinding(FragmentPetsBinding::bind)

    @Inject
    lateinit var petsViewModel: PetsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = petsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}