package com.mishok.polygo.di

import com.mishok.polygo.ui.building_card.BuildingBottomSheetDialogFragment
import com.mishok.polygo.ui.employee_card.EmployeeBottomSheetDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeEmployeeBottomSheetDialogFragment(): EmployeeBottomSheetDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeBuildingBottomSheetDialogFragment(): BuildingBottomSheetDialogFragment
}