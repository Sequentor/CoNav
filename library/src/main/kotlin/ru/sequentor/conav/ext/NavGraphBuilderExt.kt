package ru.sequentor.conav.ext

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.screen.Screen
import ru.sequentor.conav.screen.route
import ru.sequentor.conav.screen.toNavArgument

internal fun NavGraphBuilder.addDestination(screen: Screen) {
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

