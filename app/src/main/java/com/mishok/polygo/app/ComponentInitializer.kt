package com.mishok.polygo.app

import com.mishok.polygo.utils.ComponentRegistry

class ComponentInitializer(private val app: PolyGoApplication) {

    fun initAppComponent() {
        ComponentRegistry.register {
            PolyGoApplication
        }
    }
}