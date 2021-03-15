package com.mishok.polygo.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import javax.inject.Inject


class SearchFragment : BaseFragment<SearchState, SearchViewModel>() {

    override val viewModel: SearchViewModel by lazyViewModel()

    override val layoutRes: Int = R.layout.fragment_bookmarks

    override fun onStateChange(state: SearchState) {
        TODO("Not yet implemented")
    }

}