package com.example.marvelcharcterapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MarvelCharacter
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.databinding.RowCharacterBinding

class CharacterListAdapter(private var characterList:List<MarvelCharacter>,private val listener:MarvelItemClickListener): RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding:RowCharacterBinding?=DataBindingUtil.bind(view)
        init {
            view.tag=binding
        }
    }

    interface MarvelItemClickListener{
        fun onClick(data:MarvelCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.row_character,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding?.click=listener
        holder.binding?.data=characterList[position]

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateAdapter(value: List<MarvelCharacter>){
        characterList=value
         notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "CharacterListAdapter"
    }
}