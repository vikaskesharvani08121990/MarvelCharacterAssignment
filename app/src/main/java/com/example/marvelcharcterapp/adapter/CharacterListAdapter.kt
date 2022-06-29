package com.example.marvelcharcterapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MarvelCharacter
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.databinding.RowCharacterBinding

class CharacterListAdapter(
    private var characterList: List<MarvelCharacter>,
    private val click : (MarvelCharacter) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.MarvelCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false)
        return MarvelCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val data=characterList[position]
        holder.binding?.data = data
        holder.binding?.cvMain!!.setOnClickListener {
            click(data)
        }

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(value: List<MarvelCharacter>) {
        characterList = value
        notifyDataSetChanged()
    }

    inner class MarvelCharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: RowCharacterBinding? = DataBindingUtil.bind(view)

        init {
            view.tag = binding
        }
    }


}