package com.example.marvelcharcterapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.base.BaseFragment
import com.example.common.utils.ViewUtils
import com.example.common.utils.network.NetworkStatus
import com.example.core.coreComponent
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.databinding.LayoutFragmentCharacterDetailsBinding
import com.example.marvelcharcterapp.di.DaggerCharacterAppComponent
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharacterDetailsViewModel
import javax.inject.Inject

class MarvelCharacterDetailsFragment: BaseFragment() {
    private var characterId: Int=0

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var binding:LayoutFragmentCharacterDetailsBinding
    lateinit var viewModel:GetMarvelCharacterDetailsViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharacterAppComponent.factory().create(this@MarvelCharacterDetailsFragment.coreComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.layout_fragment_character_details,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireArguments().containsKey("characterId")){
             characterId=requireArguments().getInt("characterId")
        }
        viewModel= ViewModelProvider(this,factory)[GetMarvelCharacterDetailsViewModel::class.java]
        if(characterId!=0)
        viewModel.getMarvelCharacterDetails(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY,System.currentTimeMillis(),characterId)

        viewModel.marvelCharacterDetails.observe(viewLifecycleOwner, Observer {networkState->
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
                    networkState.data?.let { data->
                        if(data.charactersList.isNotEmpty())
                            binding.data=data.charactersList[0]
                    }


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

}