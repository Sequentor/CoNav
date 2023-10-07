package ru.sequentor.conav.ext

import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.destination.route
import ru.sequentor.conav.destination.toBundle

internal fun NavHostController.navigate(destination: Destination) {
    val route: String = findOrCreateRoute(destination)
    val node: NavDestination = this
        .graph
        .findNode(route) ?: error("Not found node for route: $route")

    navigate(resId = node.id, args = destination.toBundle())
}

internal fun NavHostController.back() {
    popBackStack()
}

internal fun NavHostController.backTo(destination: Destination) = popBackStack(
    route = destination.route,
    inclusive = false,
    saveState = false
)

internal fun NavHostController.replace(destination: Destination) = navigate(destination.route) {
    val currentRoute = currentBackStackEntry
        ?.destination
        ?.route
    if (currentRoute != null) {
        popUpTo(route = currentRoute) { inclusive = true }
    }
}

internal fun NavHostController.popUpTo(destination: Destination) = navigate(route = findOrCreateRoute(destination)) {
    popUpTo(id = graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

private fun NavHostController.findOrCreateRoute(destination: Destination): String {
    val destinationRoute: String = destination.route
    val node: NavDestination? = graph.findNode(destinationRoute)
    if (node == null) addDestination(destination)
    return destinationRoute
}

private fun NavHostController.addDestination(destination: Destination) {
    val navDestination = ComposeNavigator.Destination(
        navigator = navigatorProvider[ComposeNavigator::class],
        content = { destination.screenContent.invoke() }
    ).apply {
        route = destination.route
    }
    graph.addDestination(navDestination)
}
