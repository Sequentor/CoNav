package ru.sequentor.conav.ext

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.destination.route
import ru.sequentor.conav.destination.toNavArgument

internal fun NavGraphBuilder.addStartDestination(destination: Destination) {
    addDestination(
        ComposeNavigator.Destination(
            navigator = provider[ComposeNavigator::class],
            content = { destination.screenContent.invoke() }
        ).apply {
            route = destination.route
            addArgument(destination.route, destination.toNavArgument())
        }
    )
}

