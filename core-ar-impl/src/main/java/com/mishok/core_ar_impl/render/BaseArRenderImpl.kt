package com.mishok.core_ar_impl.render

import android.content.Context
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import javax.inject.Inject

import com.google.ar.sceneform.FrameTime

import android.util.Log
import com.google.ar.core.*
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ViewRenderable
import com.mishok.core_ar_api.renderer.BaseArRender
import com.mishok.core_ar_api.renderer.DoorPathRenderer
import com.mishok.core_ar_impl.R


class BaseArRenderImpl @Inject constructor(
    private val doorPathRenderer: DoorPathRenderer
) : BaseArRender {

    private var andyRenderable: ModelRenderable? = null
    private var doorRenderable: ViewRenderable? = null
    private var baseAnchor: Anchor? = null

    lateinit var fragment: ArFragment
    lateinit var context: Context

    var doorPressed = false

    override fun initRender(arFragment: ArFragment, context: Context) {
        this.fragment = arFragment
        this.context = context
    }

    override fun start() {
        fragment.arSceneView?.scene?.addOnUpdateListener(this::onUpdateFrame)
    }

    override fun drawDoor() {
        doorPressed = true
        doorRenderable = null
    }

    override fun drawNearBuildingDoor() {
        ViewRenderable.builder()
            .setView(context, R.layout.item_door_ar_layout)
            .build()
            .thenAccept {
                doorPressed = false
                addNodeToScene(createViewRenderable(it), 5f, 5f, 5f)
            }.exceptionally {
                Log.e(TAG, "Unable to load View Renderable: ", it)
                null
            }
    }

    private fun onUpdateFrame(frameTime: FrameTime) {
        val frame: Frame? = fragment.arSceneView.arFrame
        if (frame?.camera?.trackingState == TrackingState.TRACKING) {
            if (baseAnchor == null) {
                initBaseAnchor()
            }
            if (andyRenderable == null) {
                initAndy()
            }
            if (doorRenderable == null && doorPressed) {
                doorPathRenderer.init(fragment, context, baseAnchor)
                drawNearBuildingDoor()
            }

        }
    }

    private fun initBaseAnchor() {
        baseAnchor = fragment.arSceneView.session?.createAnchor(Pose.IDENTITY)
    }

    private fun initAndy() {
        ModelRenderable.builder()
            .setSource(context, R.raw.vk)
            .build()
            .thenAccept { renderable ->
                addNodeToScene(createRenderable(renderable), 5f, 5f, 5f)
            }
            .exceptionally {
                Log.e(TAG, "Unable to load Renderable: ", it)
                null
            }
    }

    private fun createRenderable(renderable: ModelRenderable?): Renderable? {
        andyRenderable = renderable
        return andyRenderable
    }

    private fun createViewRenderable(renderable: ViewRenderable?): Renderable? {
        doorRenderable = renderable
        return doorRenderable
    }

    /***
     * Function to add a child anchor to a new scene.
     */
    private fun addNodeToScene(
        renderableObject: Renderable?,
        scaleX: Float = 1f,
        scaleY: Float = 1f,
        scaleZ: Float = 1f,
        positionX: Float = 0f,
        positionY: Float = 0f,
        positionZ: Float = 0f
    ) {
        val anchorNode = AnchorNode(baseAnchor)
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.scaleController.minScale = 0.1f
        transformableNode.scaleController.maxScale = 20f
        transformableNode.renderable = renderableObject
        transformableNode.worldScale = Vector3(scaleX, scaleY, scaleZ)
        transformableNode.localPosition = Vector3(0f, -1f, 0f)
        transformableNode.setParent(anchorNode)
        fragment.arSceneView?.scene?.addChild(anchorNode)
        transformableNode.select()
    }

    companion object {
        private var TAG = BaseArRenderImpl::class.java.simpleName
    }

}