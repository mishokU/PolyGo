package com.mishok.polygo.start

import android.os.Bundle
import android.view.View
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment

class StartFragment : BaseFragment<StartState, StartViewModel>() {

    override val viewModel: StartViewModel by lazyViewModel()

    override val layoutRes: Int = R.layout.fragment_start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

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