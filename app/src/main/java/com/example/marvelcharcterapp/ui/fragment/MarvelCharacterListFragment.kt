package com.example.marvelcharcterapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.common.base.BaseFragment
import com.example.common.utils.ViewUtils
import com.example.common.utils.network.NetworkStatus
import com.example.core.coreComponent
import com.example.domain.model.MarvelCharacter
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.adapter.CharacterListAdapter
import com.example.marvelcharcterapp.databinding.LayoutFragmentCharacterListBinding
import com.example.marvelcharcterapp.di.DaggerCharacterAppComponent
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharactersViewModel
import javax.inject.Inject

class MarvelCharacterListFragment:BaseFragment() ,CharacterListAdapter.MarvelItemClickListener{


    @Inject
    lateinit var factory: ViewModelProvider.Factory



       lateinit var viewModel: GetMarvelCharactersViewModel
    lateinit var binding: LayoutFragmentCharacterListBinding
    lateinit var adapter: CharacterListAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharacterAppComponent.factory().create(this@MarvelCharacterListFragment.coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.layout_fragment_character_list,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this,factory)[GetMarvelCharactersViewModel::class.java]
        viewModel.getMarvelCharacters(BuildConfig.PUBLIC_KEY,BuildConfig.PRIVATE_KEY,System.currentTimeMillis())
        adapter= CharacterListAdapter(ArrayList(),this)
        binding.adapter=adapter
        viewModel.marvelCharacterList.observe(viewLifecycleOwner, Observer {networkState->
            when(networkState){
                is NetworkStatus.Loading->{
                    showLoading()
                }
                is NetworkStatus.Error->{
                    hideLoading()
                    ViewUtils.showToast(requireContext(),networkState.errorMessage?:"")

                }
                is NetworkStatus.Success->{
                    hideLoading()
                    if(networkState.data!=null&&networkState.data!!.charactersList.isNotEmpty())
                    adapter.updateAdapter(networkState.data!!.charactersList)


                }
            }

        })
    }

    override fun showLoading() {
        binding.progressBar.visibility=View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility=View.GONE
    }

    override fun onClick(data: MarvelCharacter) {
        var action=MarvelCharacterListFragmentDirections.actionMarvelCharacterListFragmentToMarvelCharacterDetailsFragment(characterId = data.id)
        findNavController().navigate(action)
    }





}