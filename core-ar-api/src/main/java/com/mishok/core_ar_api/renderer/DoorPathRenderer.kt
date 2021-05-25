package com.mishok.core_ar_api.renderer

import android.content.Context
import com.google.ar.core.Anchor
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment

interface DoorPathRenderer {

    fun init(fragment: ArFragment, context: Context, baseAnchor: Anchor?)

    fun calculatePath(): List<Pair<Float, Float>>

    fun drawItems(viewRenderable: ViewRenderable)
    fun drawItemDoorPath()
}