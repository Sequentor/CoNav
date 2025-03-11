package ru.sequentor.conav.ext

import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.destination.toBundle
import ru.sequentor.conav.destination.toNavArgument

internal fun NavHostController.back() {
    popBackStack()
}

internal fun NavHostController.backTo(key: String) {
    val backStackRoute: String = graph.find { it.route == key }?.route ?: return
    popBackStack(route = backStackRoute, inclusive = false)
}

internal fun NavHostController.forward(destination: Destination) {
    val route: String = findOrCreateRoute(destination = destination)
    val node: NavDestination = this
        .graph
        .findNode(route = route) ?: error("Not found node for route: $route")

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

internal fun NavHostController.popUpTo(destination: Destination) {
    navigate(route = findOrCreateRoute(destination = destination)) {
        popUpTo(id = graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

internal fun NavHostController.root(destination: Destination) {
    navigate(route = findOrCreateRoute(destination = destination)) {
        popUpTo(id = graph.findStartDestination().id) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

internal fun NavHostController.rootGraph(destination: Destination) {
    val route: String = findOrCreateRoute(destination = destination)
    navigate(route = route) {
        popUpTo(graph.startDestinationId) { inclusive = true }
    }
    graph.setStartDestination(route)
}

private fun NavHostController.findOrCreateRoute(destination: Destination): String {
    val node: NavDestination? = graph.findNode(route = destination.key)
    if (node == null) addDestination(destination = destination)
    return destination.key
}

private fun NavHostController.addDestination(destination: Destination) {
    graph.addDestination(
        ComposeNavigator.Destination(
            navigator = navigatorProvider[ComposeNavigator::class],
            content = { destination.Content() },
        ).apply {
            route = destination.key
            addArgument(destination.key, destination.toNavArgument())
        }
    )
}