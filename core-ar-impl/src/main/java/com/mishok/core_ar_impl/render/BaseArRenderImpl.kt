package com.mishok.core_ar_impl.render

import android.content.Context
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import javax.inject.Inject
import com.google.ar.core.TrackingState

import com.google.ar.core.AugmentedImage

import com.google.ar.sceneform.FrameTime

import android.os.Build
import com.google.ar.core.Frame
import com.mishok.core_ar_impl.R


class BaseArRenderImpl @Inject constructor(

) : BaseArRender {

    private var andyRenderable: ModelRenderable? = null

    lateinit var fragment: ArFragment
    lateinit var context: Context

    override fun initRender(arFragment: ArFragment, context: Context) {
        this.fragment = arFragment
        this.context = context
    }

    override fun start() {
        fragment.arSceneView.scene.addOnUpdateListener(this::onUpdateFrame)
    }

    private fun onUpdateFrame(frameTime: FrameTime) {
        val frame: Frame? = fragment.arSceneView.arFrame
        if (frame?.camera?.trackingState == TrackingState.TRACKING) {
            if (andyRenderable == null) {
                initAndy()
            }
        }
    }

    private fun initAndy() {
        ModelRenderable.builder()
            .setSource(context, R.raw.vk)
            .build()
    }

    /***
     * Function to add a child anchor to a new scene.
     */
    private fun addNodeToScene(fragment: ArFragment, anchor: Anchor, renderableObject: Renderable) {
        val anchorNode = AnchorNode(anchor)
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.renderable = renderableObject
        transformableNode.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()
    }

}