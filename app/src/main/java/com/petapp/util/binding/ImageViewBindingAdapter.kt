package com.petapp.util.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["petImageUrl", "placeHolder"])
    fun setCookModeIcon(imageView: ImageView, imageUrl: String?, @DrawableRes placeHolder: Int) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .error(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        } else {
            imageView.setImageResource(placeHolder)
        }
    }

}