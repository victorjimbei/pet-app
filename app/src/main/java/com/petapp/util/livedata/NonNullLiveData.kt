package com.petapp.util.livedata

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class NonNullLiveData<T>(private val defaultValue: T) : MutableLiveData<T>() {

    @NonNull
    override fun getValue(): T = super.getValue() ?: defaultValue

    fun observe(owner: LifecycleOwner, body: (T) -> Unit) {
        observe(owner, Observer {
            body(it ?: defaultValue)
        })
    }

    fun update(block: (T) -> T) {
        value = block(value)
    }
}
