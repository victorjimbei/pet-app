package com.petapp.home.listener

import com.petapp.home.model.PetUi

interface OnPetClickListener {
    fun onPetClicked(petUi: PetUi)
}