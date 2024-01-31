package com.petapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.petapp.databinding.PetItemViewBinding
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.model.PetUi

class PetsAdapter(
    private val petClickListener: OnPetClickListener,
) : PagingDataAdapter<PetUi, PetsAdapter.PetViewHolder>(PetsDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        return PetViewHolder(PetItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.binding.petUi = getItem(position)
        holder.binding.clickHandler = petClickListener
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