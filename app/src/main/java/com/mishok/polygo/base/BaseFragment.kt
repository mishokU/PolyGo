package com.mishok.polygo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mishok.polygo.base.api.BaseViewModel
import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.utils.SharedViewModelFactory
import com.mishok.polygo.utils.lifecycleAwareLazy
import com.mishok.polygo.utils.retrieveSharedViewModel
import com.mishok.polygo.utils.retrieveViewModel
import dagger.android.support.DaggerFragment


abstract class BaseFragment<VS : BaseViewState, VM : BaseViewModel<VS>> : DaggerFragment(), LifecycleOwner {

    var viewModelFactory: ViewModelProvider.Factory = SharedViewModelFactory()

    protected abstract val viewModel: VM

    abstract val layoutRes: Int

    protected inline fun <reified VM : ViewModel> lazyViewModel(): Lazy<VM> =
            lifecycleAwareLazy(this) {
                retrieveViewModel(viewModelFactory)
            }

    protected inline fun <reified VM : ViewModel> lazySharedViewModel(): Lazy<VM> =
            lifecycleAwareLazy(this) {
                retrieveSharedViewModel(viewModelFactory)
            }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        onBackPressed()
                    }
                })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeState(::onStateChange)
        observeNavigationEvent()
    }

    private fun observeState(callback: (state: VS) -> Unit) {
        val observer = Observer<VS> { state -> callback.invoke(state) }
        viewModel.viewState.observe(viewLifecycleOwner, observer)
        observer.onChanged(viewModel.state)     // Deliver initial state because initial state was initialized when there wasn't an observer observing state live data.
    }

    /** Implement this in subclasses to listen to state changes */
    protected abstract fun onStateChange(state: VS)

    private fun observeNavigationEvent() {
        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer { navEvent ->
            val consume = navEvent?.consume()
            consume?.invoke(findNavController())
        })
    }

    protected fun onBackPressed() {
        viewModel.onBackPressed()
        onReturnToPreviousScreen()
    }

    protected open fun onReturnToPreviousScreen() {
        findNavController().popBackStack()
    }
}