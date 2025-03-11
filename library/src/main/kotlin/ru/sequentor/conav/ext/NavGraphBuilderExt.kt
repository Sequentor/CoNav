package ru.sequentor.conav.ext

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.destination.toNavArgument

internal fun NavGraphBuilder.addDestination(destination: Destination) {
    addDestination(
        ComposeNavigator.Destination(
            navigator = provider[ComposeNavigator::class],
            content = { destination.Content() }
        ).apply {
            route = destination.key
            addArgument(argumentName = destination.key, argument = destination.toNavArgument())
        }
    )
}