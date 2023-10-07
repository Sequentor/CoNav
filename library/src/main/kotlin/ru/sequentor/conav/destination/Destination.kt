package ru.sequentor.conav.destination

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgument
import androidx.navigation.NavType

abstract class Destination : Parcelable {
    abstract val screenContent: @Composable () -> Unit
}

inline fun <reified T : Destination> SavedStateHandle.args(): T =
    requireNotNull(get(T::class.java.canonicalName ?: error("canonicalName is null")))

internal val Destination.route: String get() = this::class.java.canonicalName ?: error("canonicalName is null")

internal fun Destination.toBundle(): Bundle = bundleOf(route to this)

internal fun Destination.toNavArgument() = NavArgument.Builder()
    .setDefaultValue(this)
    .setType(NavType.ParcelableType(Destination::class.java))
    .build()
