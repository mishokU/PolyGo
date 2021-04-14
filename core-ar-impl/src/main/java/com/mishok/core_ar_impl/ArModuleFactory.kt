package com.mishok.core_ar_impl

import com.mishok.core_ar_api.starter.ArModuleStarter
import com.mishok.core_ar_impl.starter.ArModuleStarterImpl

interface ArModuleFactory {

    companion object {
        fun starter(): ArModuleStarter {
            return ArModuleStarterImpl()
        }
    }
}