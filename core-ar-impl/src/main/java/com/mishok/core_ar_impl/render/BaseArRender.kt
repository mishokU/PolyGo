package com.mishok.core_ar_impl.render

import android.content.Context
import com.google.ar.sceneform.ux.ArFragment

interface BaseArRender {
    fun initRender(arFragment: ArFragment, context: Context)
    fun start()
}