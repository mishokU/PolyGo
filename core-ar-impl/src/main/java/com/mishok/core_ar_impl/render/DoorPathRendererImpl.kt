package com.mishok.core_ar_impl.render

import android.content.Context
import android.util.Log
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.mishok.core_ar_api.renderer.DoorPathRenderer
import com.mishok.core_ar_impl.R
import javax.inject.Inject

class DoorPathRendererImpl @Inject constructor(

) : DoorPathRenderer {

    private lateinit var fragment: ArFragment
    private lateinit var context: Context

    private var baseAnchor: Anchor? = null

    override fun init(fragment: ArFragment, context: Context, baseAnchor: Anchor?) {
        this.fragment = fragment
        this.context = context
        this.baseAnchor = baseAnchor

        drawItemDoorPath()
    }

    override fun calculatePath(): List<Pair<Float, Float>> {
        return listOf(
            Pair(0f, 0f),
            Pair(1f, 1f)
        )
    }

    override fun drawItems(viewRenderable: ViewRenderable) {
        val path = calculatePath()
        path.forEach { position ->
            addNodeToScene(viewRenderable, position = position)
        }
    }

    override fun drawItemDoorPath() {
        ViewRenderable.builder()
            .setView(context, R.layout.item_path_ar_layout)
            .build()
            .thenAccept {
                drawItems(it)
            }.exceptionally {
                Log.e(TAG, "Unable to load View Renderable: ", it)
                null
            }
    }

    private fun addNodeToScene(
        renderableObject: Renderable?,
        scaleX: Float = 1f,
        scaleY: Float = 1f,
        scaleZ: Float = 1f,
        position: Pair<Float, Float>
    ) {
        val anchorNode = AnchorNode(baseAnchor)
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.scaleController.minScale = 0.1f
        transformableNode.scaleController.maxScale = 20f
        transformableNode.renderable = renderableObject
        transformableNode.worldScale = Vector3(scaleX, scaleY, scaleZ)
        transformableNode.localPosition = Vector3(position.first, -1f, position.second)
        transformableNode.localRotation = Quaternion.axisAngle(Vector3(1.0f, 0.0f, 0.0f), 90f)
        transformableNode.setParent(anchorNode)
        fragment.arSceneView?.scene?.addChild(anchorNode)
        transformableNode.select()
    }

    companion object {
        private var TAG = DoorPathRendererImpl::class.java.simpleName
    }

}