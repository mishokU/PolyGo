package com.mishok.polygo.ui.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.base.FragmentConfiguration
import javax.inject.Inject


class BookmarksFragment : BaseFragment<BookmarksState, BookmarksViewModel>() {

    override val viewModel: BookmarksViewModel by lazyViewModel()

    override fun baseConfiguration(configuration: FragmentConfiguration) {
        super.baseConfiguration(configuration.copy(
                layoutRes = R.layout.fragment_bookmarks
        )
        )
    }

    override fun onStateChange(state: BookmarksState) {

    }

}