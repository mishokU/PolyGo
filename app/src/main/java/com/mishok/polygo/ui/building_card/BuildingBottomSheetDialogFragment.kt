package com.mishok.polygo.ui.building_card

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mishok.polygo.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_bottom_sheet_building.*
import javax.inject.Inject

class BuildingBottomSheetDialogFragment : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    private val buildingName: String = arguments?.getString(KEY_BUILDING_NAME).toString()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_building, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        buildingNameTv.text = arguments?.getString(KEY_BUILDING_NAME).toString()
    }

    data class NavigationData(
        val buildingName: String
    )

    companion object {

        const val KEY_BUILDING_NAME = "key_building_name"

        @JvmStatic
        fun newInstance(data: NavigationData): BuildingBottomSheetDialogFragment {
            val bundle = Bundle()
            bundle.putString(KEY_BUILDING_NAME, data.buildingName)
            val fragment = BuildingBottomSheetDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}