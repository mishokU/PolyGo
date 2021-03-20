package com.mishok.polygo.ui.radar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.base.FragmentConfiguration
import javax.inject.Inject


class RadarFragment : BaseFragment<RadarState, RadarViewModel>() {

    override val viewModel: RadarViewModel by lazyViewModel()

    override fun baseConfiguration(configuration: FragmentConfiguration) {
        super.baseConfiguration(configuration.copy(
                layoutRes = R.layout.fragment_radar
        ))
    }

    override fun onStateChange(state: RadarState) {

    }


}