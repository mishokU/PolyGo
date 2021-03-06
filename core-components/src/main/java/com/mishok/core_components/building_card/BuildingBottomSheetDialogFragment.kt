package com.mishok.core_components.building_card

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mishok.core_components.R
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

    private val resultListener: BuildingListener
        get() = parentFragment as BuildingListener

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
        buildingTimeTv.text = arguments?.getString(KEY_BUILDING_TIME).toString()
        insideBuildingButton.setOnClickListener {
            resultListener.navigateToBuildingInside()
            this.dismiss()
        }
        showEntranceButton.setOnClickListener {
            resultListener.showBuildingEntrance()
            this.dismiss()
        }
    }

    data class NavigationData(
        val buildingName: String,
        val time: String,
        val buildingId: Long
    )

    companion object {

        const val KEY_BUILDING_NAME = "key_building_name"
        const val KEY_BUILDING_TIME = "key_building_time"
        const val KEY_BUILDING_ID = "key_building_id"

        @JvmStatic
        fun newInstance(data: NavigationData): BuildingBottomSheetDialogFragment {
            val bundle = Bundle()
            bundle.putString(KEY_BUILDING_NAME, data.buildingName)
            bundle.putLong(KEY_BUILDING_ID, data.buildingId)
            bundle.putString(KEY_BUILDING_TIME, data.time)
            val fragment = BuildingBottomSheetDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}