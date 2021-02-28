package com.mishok.polygo.utils

/**
 * Motivation
 *
 * If we replace fragment A with fragment B using following code:
 * getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragementB).commit();
 *
 * We should have this lifecycle:
 * Fragment B: onAttach -> onCreate
 * Fragment A: onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
 * Fragment B: onCreateView -> onActivityCreated -> onStart -> onResume
 *
 * And if both fragment are of the same type we basically have hard time getting new component/clearing existing one
 * due to the singleton nature of components held by ComponentRegistry
 *
 * Therefore we should do couple simple things:
 * 1. Clear component before creating new one
 * 2. Create new components with owner tag
 * 3. Distinguish components by owner tag before attempting to clear
 *
 * Never forget this unless you want to have terrible component leaks.
 */

object ComponentRegistry {

    val holder = ComponentHolder()

    inline fun <reified T> get(ownerTag: String? = null) = holder.get<T>(ownerTag)
    inline fun <reified T> clear(ownerTag: String? = null) = holder.clear<T>(ownerTag)
    inline fun <reified T> hasComponent(ownerTag: String? = null) = holder.hasComponent<T>(ownerTag)
    inline fun <reified T : Any> register(ownerTag: String, crossinline getComponent: () -> T) =
        holder.register(ownerTag, getComponent)

    inline fun <reified T : Any> register(crossinline getComponent: () -> T) =
        holder.register(getComponent)

    inline fun <reified T : Any> replaceComponentIfExists(ownerTag: String? = null, component: T) =
        holder.replaceComponentIfExists(ownerTag, component)
}
