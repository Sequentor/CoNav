package ru.sequentor.conav.ext

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.screen.Destination
import ru.sequentor.conav.screen.route
import ru.sequentor.conav.screen.toNavArgument

internal fun NavGraphBuilder.addDestination(destination: Destination) {
    addDestination(
        ComposeNavigator.Destination(
            navigator = provider[ComposeNavigator::class],
            content = { destination.content.invoke() }
        ).apply {
            route = destination.route
            addArgument(destination.route, destination.toNavArgument())
        }
    )
}