package ru.sequentor.composenavigation.core.navigation.destination

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

abstract class Destination {

    abstract val route: String

    abstract val screenContent: @Composable () -> Unit

    open fun composable(navGraphBuilder: NavGraphBuilder, vararg arguments: NamedNavArgument) {
        navGraphBuilder.composable(
            route = route,
            arguments = arguments.toList()
        ) {
            screenContent.invoke()
        }
    }
}
