package com.mishok.core_ar_api.renderer

import android.content.Context
import com.google.ar.sceneform.ux.ArFragment

interface BaseArRender {
    fun initRender(arFragment: ArFragment, context: Context)

    fun start()

    fun drawDoor()

    fun drawNearBuildingDoor()
}