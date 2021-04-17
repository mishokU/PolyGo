package com.mishok.polygo.ui.employee_card

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_bottom_sheet_item.*
import javax.inject.Inject

class EmployeeBottomSheetDialogFragment : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        employeeName.text = arguments?.get(KEY_NAME).toString()
        employeeDescription.text = arguments?.get(KEY_POSITION).toString()
        employeeEmailButton.text = arguments?.get(KEY_EMAIL).toString()

        //Open gmail to write message
        employeeEmailButton.setOnClickListener {

        }

        //Open web view with schedule
        employeeScheduleButton.setOnClickListener {

        }
    }

    data class NavigationData(
        val name: String,
        val email: String,
        val schedule: String,
        val avatar: String,
        val position: String
    )

    companion object {

        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_SCHEDULE = "schedule"
        private const val KEY_AVATAR = "avatar"
        private const val KEY_POSITION = "position"

        @JvmStatic
        fun newInstance(data: NavigationData): EmployeeBottomSheetDialogFragment {
            val fragment = EmployeeBottomSheetDialogFragment()
            val bundle = Bundle()
            bundle.putString(KEY_EMAIL, data.email)
            bundle.putString(KEY_AVATAR, data.avatar)
            bundle.putString(KEY_POSITION, data.position)
            bundle.putString(KEY_NAME, data.name)
            bundle.putString(KEY_SCHEDULE, data.schedule)
            fragment.arguments = bundle
            return fragment
        }
    }
}