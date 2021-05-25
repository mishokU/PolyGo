package com.mishok.core_ar_impl.di

import com.mishok.core_ar_api.starter.ArModuleStarter
import com.mishok.core_ar_api.renderer.BaseArRender
import com.mishok.core_ar_impl.render.BaseArRenderImpl
import com.mishok.core_ar_api.renderer.DoorPathRenderer
import com.mishok.core_ar_impl.render.DoorPathRendererImpl
import com.mishok.core_ar_impl.starter.ArModuleStarterImpl
import dagger.Binds
import dagger.Module

@Module
interface ArFeatureModule {

    @Binds
    fun bindStarter(impl: ArModuleStarterImpl): ArModuleStarter

    @Binds
    fun bindBaseArRender(impl: BaseArRenderImpl): BaseArRender

    @Binds
    fun bindDoorPathRenderable(impl: DoorPathRendererImpl): DoorPathRenderer

}