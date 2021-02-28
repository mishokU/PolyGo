package com.mishok.core_impl.utils

import android.content.Context
import com.mishok.core_api.utils.LocaleManager
import java.util.*
import javax.inject.Inject

class LocaleManagerImpl @Inject constructor(
    private val context: Context
) : LocaleManager {

    override fun setLocale() {
        val resources = context.resources
        val config = resources.configuration
        val locale = Locale("ru")

        Locale.setDefault(locale)
        config?.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

}