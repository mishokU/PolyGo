package com.mishok.core_ar_impl.ui

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.ar.sceneform.ux.ArFragment
import com.mishok.common_registry.ComponentRegistry
import com.mishok.core_api.tags.ModuleTags
import com.mishok.core_ar_impl.R
import com.mishok.core_ar_impl.di.ArFeatureComponent
import com.mishok.core_ar_api.renderer.BaseArRender
import kotlinx.android.synthetic.main.fragment_base_ar.*
import javax.inject.Inject


class PolyGoArFragment : Fragment() {

    private lateinit var arFragment: ArFragment

    @Inject
    lateinit var render: BaseArRender

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!checkIsSupportedDeviceOrFinish(requireActivity())) {
            findNavController().popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentRegistry.get<ArFeatureComponent>(ModuleTags.AR).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_ar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScene()
        initViews()
    }

    private fun initScene() {
        arFragment = childFragmentManager.findFragmentById(R.id.arFragmentView) as ArFragment
        arFragment.apply {
            arSceneView.planeRenderer.isVisible = true
            planeDiscoveryController.hide()
            render.initRender(arFragment, requireContext())
            render.start()
        }
    }

    private fun initViews() {
        doorArButton.setOnClickListener {
            render.drawDoor()
        }

        closeARButton.setOnClickListener {
            this.findNavController().popBackStack()
        }

        myLocationArButton.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ComponentRegistry.clear<ArFeatureComponent>(ModuleTags.AR)
        arFragment.arSceneView.destroy()
    }

    override fun onStop() {
        super.onStop()
        arFragment.arSceneView.pause()
    }

    private fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        val openGlVersionString =
            (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion

        if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later")
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                .show()
            activity.finish()
            return false
        }
        return true
    }

    companion object {
        private val TAG: String = PolyGoArFragment::class.java.simpleName
        private val MIN_OPENGL_VERSION = 3.0
    }
}