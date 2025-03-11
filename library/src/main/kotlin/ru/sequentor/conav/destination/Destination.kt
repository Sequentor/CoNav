package ru.sequentor.conav.destination

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import java.io.Serializable

interface Screen {
    val key: String get() = this::class.java.name
}

interface Destination : Screen, Serializable {
    @Composable
    fun Content()
}

inline fun <reified T : Destination> SavedStateHandle.optionalArgs(): T? {
    val key: String = keys().firstOrNull() ?: return null
    return get(key)
}

inline fun <reified T : Destination> SavedStateHandle.requiredArgs(): T {
    val key: String = keys().firstOrNull() ?: error("no arguments for this Destination")
    return requireNotNull(get(key))
}

internal fun Destination.toBundle(): Bundle = bundleOf(key to this)

internal fun Destination.toNavArgument() = NavArgument.Builder()
    .setDefaultValue(this)
    .setType(NavType.SerializableType(Destination::class.java))
    .build()