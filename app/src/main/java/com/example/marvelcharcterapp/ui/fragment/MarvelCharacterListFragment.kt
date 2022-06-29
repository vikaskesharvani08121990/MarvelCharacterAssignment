package com.example.marvelcharcterapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appcommon.base.BaseFragment
import com.example.appcommon.utils.NetworkResponse
import com.example.approot.rootComponent
import com.example.domain.model.MarvelCharacter
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.adapter.CharacterListAdapter
import com.example.marvelcharcterapp.databinding.LayoutFragmentCharacterListBinding
import com.example.marvelcharcterapp.di.DaggerCharacterAppComponent
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharactersViewModel
import javax.inject.Inject

class MarvelCharacterListFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: GetMarvelCharactersViewModel

    lateinit var adapter: CharacterListAdapter

    private lateinit var binding: LayoutFragmentCharacterListBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharacterAppComponent.factory()
            .create(this@MarvelCharacterListFragment.rootComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_fragment_character_list,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[GetMarvelCharactersViewModel::class.java]
        viewModel.getMarvelCharacters(
            BuildConfig.PUBLIC_KEY,
            BuildConfig.PRIVATE_KEY,
            System.currentTimeMillis()
        )
        adapter = CharacterListAdapter(ArrayList()) { marvelCharacter ->
            val action =
                MarvelCharacterListFragmentDirections.actionMarvelCharacterListFragmentToMarvelCharacterDetailsFragment(
                    characterId = marvelCharacter.id
                )
            findNavController().navigate(action)
        }
        binding.adapter = adapter
        viewModel.marvelCharacterList.observe(viewLifecycleOwner) { networkState ->
            when (networkState) {
                is NetworkResponse.Loading -> {
                    showProgress()
                }
                is NetworkResponse.Error -> {
                    hideProgress()
                    showMessage( networkState.errorMessage ?: getErrorMessage(networkState.errorCode))
                }
                is NetworkResponse.Success -> {
                    hideProgress()
                    if (networkState.data != null && networkState.data!!.isNotEmpty())
                        adapter.updateAdapter(networkState.data!!)
                }
            }

        }
    }



    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }


}