package com.mishok.polygo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.mishok.polygo.base.api.BaseViewModel
import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.utils.SharedViewModelFactory
import com.mishok.polygo.utils.lifecycleAwareLazy
import com.mishok.polygo.utils.retrieveSharedViewModel
import com.mishok.polygo.utils.retrieveViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment


abstract class BaseFragment<VS : BaseViewState, VM : BaseViewModel<VS>> : DaggerFragment(), LifecycleOwner {

    var viewModelFactory: ViewModelProvider.Factory = SharedViewModelFactory()

    protected abstract val viewModel: VM

    protected var asyncAdapter: AsyncListDifferDelegationAdapter<Any>? = null
    protected var layoutRes: Int? = null
    protected var recycerView: Any? = null
    protected var orientation: Int = RecyclerView.VERTICAL

    open fun baseConfiguration(configuration: FragmentConfiguration) {
        this.layoutRes = configuration.layoutRes
        this.asyncAdapter = configuration.adapter
        this.recycerView = configuration.recyclerView
        this.orientation = configuration.orientation
    }

    protected inline fun <reified VM : ViewModel> lazyViewModel(): Lazy<VM> =
            lifecycleAwareLazy(this) {
                retrieveViewModel(viewModelFactory)
            }

    protected inline fun <reified VM : ViewModel> lazySharedViewModel(): Lazy<VM> =
            lifecycleAwareLazy(this) {
                retrieveSharedViewModel(viewModelFactory)
            }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutRes?.let { inflater.inflate(it, container, false) }
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
        initList()
    }

    private fun initList() = with(recycerView as RecyclerView) {
        layoutManager = LinearLayoutManager(context, orientation, false)
        adapter = asyncAdapter
        setHasFixedSize(true)
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
        viewModel.navigationEvent.observe(viewLifecycleOwner, { navEvent ->
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