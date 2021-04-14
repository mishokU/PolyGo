package com.mishok.common_registry

import timber.log.Timber
import kotlin.reflect.KClass

class ComponentHolder {

    private val components = HashMap<String, Any>()

    fun get(key: KClass<*>, tag: String?): Any {
        val component = getMapKeyForComponent(key, tag)
        Timber.v("GET|$component|ALL = ${components.keys}")
        return components.getValue(component)
    }

    fun clear(key: KClass<*>, tag: String?) {
        val component = getMapKeyForComponent(key, tag)
        Timber.v("CLEAR|$component|ALL = ${components.keys}")
        components.remove(component)
            ?: throw IllegalArgumentException("Wasn't able to clear component of class: $key with tag: $tag")
    }

    fun hasComponent(key: KClass<*>, tag: String?) =
        components.containsKey(getMapKeyForComponent(key, tag))

    fun register(key: KClass<*>, getComponent: () -> Any, ownerTag: String?) {
        val component = getMapKeyForComponent(key, ownerTag)
        Timber.v("REGISTER|$component|ALL = ${components.keys}")

        require(
            !hasComponent(key, ownerTag)
        ) { "Component for key = $key tag = $ownerTag is already registered!" }

        components[component] = getComponent()
    }

    inline fun <reified T : Any> replaceComponentIfExists(ownerTag: String? = null, component: T) {
        if (hasComponent(T::class, ownerTag)) clear(T::class, ownerTag)
        register(
            key = T::class,
            getComponent = { component },
            ownerTag = ownerTag
        )
    }

    inline fun <reified T> get(ownerTag: String? = null) = get(T::class, ownerTag) as T
    inline fun <reified T> clear(ownerTag: String? = null) = clear(T::class, ownerTag)
    inline fun <reified T> hasComponent(ownerTag: String? = null) = hasComponent(T::class, ownerTag)

    inline fun <reified T : Any> register(ownerTag: String, crossinline getComponent: () -> T) {
        register(
            key = T::class,
            getComponent = { getComponent.invoke() },
            ownerTag = ownerTag
        )
    }

    inline fun <reified T : Any> register(crossinline getComponent: () -> T) {
        register(
            key = T::class,
            getComponent = { getComponent.invoke() },
            ownerTag = null
        )
    }

    private fun getMapKeyForComponent(key: KClass<*>, tag: String? = null): String {
        val className = key.java.name
        return tag
            ?.let {
                "$className--$it"
            }
            ?: className
    }
}
