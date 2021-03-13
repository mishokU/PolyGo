package com.mishok.polygo.start

import com.mishok.core_api.utils.LocaleManager
import com.mishok.polygo.base.BaseViewModelImpl
import javax.inject.Inject

class StartViewModel : BaseViewModelImpl<StartState>() {

    @Inject
    lateinit var localeManager: LocaleManager

    override val initialState: StartState
        get() = StartState()

    fun finishApplication() {

    }

}