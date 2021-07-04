package com.mishok.core_ar_impl.render

import android.content.Context
import android.graphics.Point
import android.net.Uri
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import javax.inject.Inject

import com.google.ar.sceneform.FrameTime

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.ar.core.*
import com.google.ar.sceneform.assets.RenderableSource
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

    lateinit var baseFragment: Fragment
    lateinit var fragment: ArFragment
    lateinit var context: Context

    var doorPressed = false

    override fun initRender(arFragment: ArFragment, fragment: Fragment, context: Context) {
        this.fragment = arFragment
        this.baseFragment = fragment
        this.context = context
    }

    override fun start() {
        fragment.arSceneView?.scene?.addOnUpdateListener(this::onUpdateFrame)
    }

    override fun drawDoor() {
        doorPressed = true
        doorRenderable = null
    }

    private fun getScreenCenter(): Point {
        return Point(512 / 2, 512 / 2)
    }

    override fun addObject(model: Uri) {
        val frame = fragment.arSceneView.arFrame
        val point = getScreenCenter()
        if (frame != null) {
            val hits = frame.hitTest(point.x.toFloat(), point.y.toFloat())
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane && trackable.isPoseInPolygon(hit.hitPose)) {
                    placeObject(fragment, hit.createAnchor(), model)
                    break
                }
            }
        }
    }

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
        ModelRenderable.builder()
            .setSource(
                fragment.context, RenderableSource.builder().setSource(
                    fragment.context,
                    model,
                    RenderableSource.SourceType.GLTF2
                ).build()
            )
            .setRegistryId(model)
            .build()
            .thenAccept {
                addNodeToScene(fragment, anchor, it)
            }
            .exceptionally {
                Toast.makeText(context, "Could not fetch model from $model", Toast.LENGTH_SHORT)
                    .show()
                return@exceptionally null
            }
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

    private fun addNodeToScene(fragment: ArFragment, anchor: Anchor, renderable: ModelRenderable) {
        val anchorNode = AnchorNode(anchor)
        // TransformableNode means the user to move, scale and rotate the model
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.renderable = renderable
        transformableNode.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()
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