package com.petapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.petapp.databinding.PetItemViewBinding
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.model.PetUi

class PetsAdapter(
    private val petClickListener: OnPetClickListener,
) : ListAdapter<PetUi, RecyclerView.ViewHolder>(PetsDiffUtilCallback) {

    private lateinit var pets: List<PetUi>

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

    object PetsDiffUtilCallback : DiffUtil.ItemCallback<PetUi>() {

        override fun areItemsTheSame(oldItem: PetUi, newItem: PetUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PetUi, newItem: PetUi): Boolean {
            return oldItem == newItem
        }
    }
}