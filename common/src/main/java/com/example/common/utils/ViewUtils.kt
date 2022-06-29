package com.example.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object ViewUtils {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty())
            Glide.with(view.context).load(url).circleCrop().into(view)
    }

    @JvmStatic
    @BindingAdapter("imageSquireUrl")
    fun loadSquireShapeImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty())
            Glide.with(view.context).load(url).override(view.width, view.height).into(view)
    }


}