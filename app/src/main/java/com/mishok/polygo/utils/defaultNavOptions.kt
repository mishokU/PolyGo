package com.mishok.polygo.utils

import androidx.navigation.NavOptions
import com.mishok.polygo.R

val defaultNavOptions: NavOptions
    get() {
        return NavOptions.Builder()
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                .build()
    }