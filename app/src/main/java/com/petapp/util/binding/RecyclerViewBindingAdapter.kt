package com.petapp.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petapp.home.adapter.PetsAdapter
import com.petapp.home.listener.OnPetClickListener
import com.petapp.home.model.PetUi

object RecyclerViewBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["pets", "clickHandler"])
    fun setPreviousCooks(recyclerView: RecyclerView, pets: List<PetUi>, petClickListener: OnPetClickListener) {
        if (recyclerView.adapter is PetsAdapter) {
            (recyclerView.adapter as PetsAdapter?)?.updateItems(pets)
        } else {
            val layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.layoutManager = layoutManager
            val previousCookCyclesAdapter = PetsAdapter(petClickListener)
            previousCookCyclesAdapter.updateItems(pets)
            recyclerView.adapter = previousCookCyclesAdapter
        }
    }

}