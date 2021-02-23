package com.mishok.polygo.db.impl.di

import dagger.Component


@Component(
    modules = [
        DbProvidersModule::class,
        DbCoreModule::class
    ]
)
interface DbCoreComponent {

    @Component
    interface DbCoreDependencies
}