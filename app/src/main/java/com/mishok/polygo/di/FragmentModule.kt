package com.mishok.polygo.di

import com.mishok.core_components.building_card.BuildingBottomSheetDialogFragment
import com.mishok.core_components.employee_card.EmployeeBottomSheetDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeEmployeeBottomSheetDialogFragment(): EmployeeBottomSheetDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeBuildingBottomSheetDialogFragment(): BuildingBottomSheetDialogFragment
}