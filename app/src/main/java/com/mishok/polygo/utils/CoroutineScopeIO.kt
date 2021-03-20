package com.mishok.polygo.utils

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeIO

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeMain
