package ru.sequentor.conav.ext

import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.screen.Destination
import ru.sequentor.conav.screen.route
import ru.sequentor.conav.screen.toBundle
import ru.sequentor.conav.screen.toNavArgument

internal fun NavHostController.back() {
    popBackStack()
}

internal fun NavHostController.root(destination: Destination) {
    navigate(route = findOrCreateRoute(destination)) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

internal fun NavHostController.forward(destination: Destination) {
    val route: String = findOrCreateRoute(destination = destination)
    val node: NavDestination = this
        .graph
        .findNode(route) ?: error("Not found node for route: $route")

    navigate(resId = node.id, args = destination.toBundle())
}

internal fun NavHostController.replace(destination: Destination) {
    navigate(route = findOrCreateRoute(destination = destination)) {

        val currentRoute: String? = currentBackStackEntry
            ?.destination
            ?.route

        if (currentRoute != null) {
            popUpTo(route = currentRoute) { inclusive = true }
        }
    }
}

internal fun NavHostController.backTo(destinationKey: String?) {
    if (destinationKey == null) {
        val route: String = graph.findStartDestination().route ?: return
        popBackStack(route = route, inclusive = false)
    } else {
        val backStackRoute: String = graph.find { it.route == destinationKey }?.route ?: return
        popBackStack(route = backStackRoute, inclusive = false)
    }
}

internal fun NavHostController.bottom(destination: Destination) {
    navigate(route = findOrCreateRoute(destination = destination)) {
        popUpTo(id = graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.findOrCreateRoute(destination: Destination): String {
    val node: NavDestination? = graph.findNode(destination.route)
    if (node == null) addDestination(destination)
    return destination.route
}

private fun NavHostController.addDestination(destination: Destination) {
    graph.addDestination(
        ComposeNavigator.Destination(
            navigator = navigatorProvider[ComposeNavigator::class],
            content = { destination.content.invoke() },
        ).apply {
            route = destination.route
            addArgument(destination.route, destination.toNavArgument())
        }
    )
}