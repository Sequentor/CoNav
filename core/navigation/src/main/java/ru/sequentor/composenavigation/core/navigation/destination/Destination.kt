package ru.sequentor.composenavigation.core.navigation.destination

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get

abstract class Destination: Parcelable {

    abstract val screenContent: @Composable () -> Unit

    fun addDestination(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.addDestination(
            ComposeNavigator.Destination(
                navigator = navGraphBuilder.provider[ComposeNavigator::class],
                content = { screenContent.invoke() }
            ).apply {
                route = this@Destination.toString()
            }
        )
    }
}

inline fun <reified T : Any> SavedStateHandle.args(): T? = get(T::class.toString())

inline fun <reified T : Any> SavedStateHandle.requiredArgs(): T = requireNotNull(get(T::class.toString()))
