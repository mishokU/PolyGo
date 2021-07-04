package com.mishok.core_ar_api.renderer

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.google.ar.sceneform.ux.ArFragment

interface BaseArRender {
    fun initRender(arFragment: ArFragment, fragment: Fragment, context: Context)

    fun start()

    fun drawDoor()

    fun drawNearBuildingDoor()

    fun addObject(model: Uri)
}