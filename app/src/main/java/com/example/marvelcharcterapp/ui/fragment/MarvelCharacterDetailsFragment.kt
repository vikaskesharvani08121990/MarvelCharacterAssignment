package com.example.marvelcharcterapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.appcommon.base.BaseFragment
import com.example.appcommon.utils.NetworkResponse
import com.example.approot.rootComponent
import com.example.marvelcharcterapp.BuildConfig
import com.example.marvelcharcterapp.R
import com.example.marvelcharcterapp.databinding.LayoutFragmentCharacterDetailsBinding
import com.example.marvelcharcterapp.di.DaggerCharacterAppComponent
import com.example.marvelcharcterapp.viewmodel.GetMarvelCharacterDetailsViewModel
import javax.inject.Inject

private const val CHARACTER_ID = "characterId"

class MarvelCharacterDetailsFragment : BaseFragment() {

    private var characterId: Int = 0

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: GetMarvelCharacterDetailsViewModel

    private lateinit var binding: LayoutFragmentCharacterDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharacterAppComponent.factory()
            .create(this@MarvelCharacterDetailsFragment.rootComponent()).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_fragment_character_details,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireArguments().containsKey(CHARACTER_ID)) {
            characterId = requireArguments().getInt(CHARACTER_ID)
        }
        viewModel = ViewModelProvider(this, factory)[GetMarvelCharacterDetailsViewModel::class.java]
        if (characterId != 0)
            viewModel.getMarvelCharacterDetails(
                BuildConfig.PUBLIC_KEY,
                BuildConfig.PRIVATE_KEY,
                System.currentTimeMillis(),
                characterId
            )

        viewModel.marvelCharacterDetails.observe(viewLifecycleOwner) { networkState ->
            when (networkState) {
                is NetworkResponse.Loading -> {
                    showProgress()
                }
                is NetworkResponse.Error -> {
                    hideProgress()
                    showMessage(
                        networkState.errorMessage ?: getErrorMessage(networkState.errorCode)
                    )
                }
                is NetworkResponse.Success -> {
                    hideProgress()
                    networkState.data?.let { data ->
                        binding.data = data
                    }
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