package com.mishok.polygo.ui.start

import android.os.Bundle
import android.view.View
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.base.FragmentConfiguration
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : BaseFragment<StartState, StartViewModel>() {

    @Inject
    override lateinit var viewModel: StartViewModel

    override val layoutRes: Int = R.layout.fragment_start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initLocalDatabase()
    }

    private fun initLocalDatabase() {
        viewModel.initLocalDatabase()
    }

    private fun initViews() {
        searchButton.setOnClickListener {
            viewModel.openSearchFragment()
        }
        bookmarkButton.setOnClickListener {
            viewModel.openBookmarkFragment()
        }
        radarButton.setOnClickListener {
            viewModel.openRadarFragment()
        }
        mapButton.setOnClickListener {
            viewModel.openMapFragment()
        }
    }

    override fun onStateChange(state: StartState) {
        if (state.finish) {
            requireActivity().finish()
        }
    }

    override fun onReturnToPreviousScreen() {
        viewModel.finishApplication()
        activity?.finish()
    }

}