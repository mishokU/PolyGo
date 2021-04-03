package com.mishok.polygo.di

import com.mishok.polygo.ui.bookmarks.BookmarksFragment
import com.mishok.polygo.ui.employee_card.EmployeeBottomSheetDialogFragment
import com.mishok.polygo.ui.map.MapsFragment
import com.mishok.polygo.ui.radar.RadarFragment
import com.mishok.polygo.ui.search.SearchFragment
import com.mishok.polygo.ui.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeRadarFragment(): RadarFragment

    @ContributesAndroidInjector
    abstract fun contributeEmployeeBottomSheetDialogFragment(): EmployeeBottomSheetDialogFragment

}