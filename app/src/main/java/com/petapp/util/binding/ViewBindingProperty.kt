@file:Suppress("RedundantVisibilityModifier")

package com.petapp.util.binding

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.annotation.RestrictTo
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

public abstract class ViewBindingProperty<in R : Any, T : ViewBinding>(
    private val viewBinder: (R) -> T
) : ReadOnlyProperty<R, T> {

    private var viewBinding: T? = null
    private val lifecycleObserver = ClearOnDestroyLifecycleObserver()
    private var thisRef: R? = null

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    public override fun getValue(thisRef: R, property: KProperty<*>): T {
        checkIsMainThread()
        viewBinding?.let { return it }

        this.thisRef = thisRef
        val lifecycle = getLifecycleOwner(thisRef).lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            mainHandler.post { viewBinding = null }
        } else {
            lifecycle.addObserver(lifecycleObserver)
        }
        return viewBinder(thisRef).also { viewBinding = it }
    }

    @MainThread
    public fun clear() {
        checkIsMainThread()

        val thisRef = thisRef ?: return
        this.thisRef = null
        getLifecycleOwner(thisRef).lifecycle.removeObserver(lifecycleObserver)
        mainHandler.post {
            viewBinding = null
        }
    }

    private inner class ClearOnDestroyLifecycleObserver : DefaultLifecycleObserver {

        @MainThread
        override fun onDestroy(owner: LifecycleOwner): Unit = clear()
    }

    private companion object {

        private val mainHandler = Handler(Looper.getMainLooper())
    }

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    internal inline val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    internal fun checkIsMainThread() = check(isMainThread)
}
