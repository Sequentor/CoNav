package ru.sequentor.conav.ext

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.destination.Screen
import ru.sequentor.conav.destination.route
import ru.sequentor.conav.destination.toNavArgument

internal fun NavGraphBuilder.addStartScreen(screen: Screen) {
    addDestination(
        ComposeNavigator.Destination(
            navigator = provider[ComposeNavigator::class],
            content = { screen.screenContent.invoke() }
        ).apply {
            route = screen.route
            addArgument(screen.route, screen.toNavArgument())
        }
    )
}

