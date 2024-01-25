package com.petapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petapp.databinding.PetItemViewBinding
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.model.PetUi

class PetsAdapter(
    private val petClickListener: OnPetClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var pets: List<PetUi>

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PetViewHolder(PetItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return pets.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PetViewHolder).binding.petUi = pets[position]
        holder.binding.clickHandler = petClickListener
    }

    fun updateItems(pets: List<PetUi>) {
        this.pets = pets
        notifyDataSetChanged()
    }

    class PetViewHolder(val binding: PetItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}