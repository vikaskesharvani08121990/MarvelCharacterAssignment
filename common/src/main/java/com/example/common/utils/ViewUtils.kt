package com.example.common.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide



object ViewUtils{
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?){
        if(!url.isNullOrEmpty())
            Glide.with(view.context).load(url).circleCrop().into(view)
    }
    @JvmStatic
    @BindingAdapter("imageSquireUrl")
    fun loadSquireShapeImage(view: ImageView, url: String?){
        if(!url.isNullOrEmpty())
            Glide.with(view.context).load(url).override(view.width,view.height).into(view)
    }

    @JvmStatic
    fun showToast(context: Context,message:String?){
        if(!message.isNullOrEmpty())
        {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }

}