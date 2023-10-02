package ru.sequentor.conav.ext

import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import ru.sequentor.conav.destination.Screen
import ru.sequentor.conav.destination.route
import ru.sequentor.conav.destination.toBundle

internal fun NavHostController.navigate(screen: Screen) {
    val route: String = findOrCreateRoute(screen)
    val node: NavDestination = this
        .graph
        .findNode(route) ?: error("Not found node for route: $route")

    navigate(resId = node.id, args = screen.toBundle())
}

internal fun NavHostController.back() {
    popBackStack()
}

internal fun NavHostController.backTo(screen: Screen) = popBackStack(
    route = screen.route,
    inclusive = false,
    saveState = false
)

internal fun NavHostController.replace(screen: Screen) = navigate(screen.route) {
    val currentRoute = currentBackStackEntry
        ?.destination
        ?.route
    if (currentRoute != null) {
        popUpTo(route = currentRoute) { inclusive = true }
    }
}

internal fun NavHostController.popUpTo(screen: Screen) = navigate(route = findOrCreateRoute(screen)) {
    popUpTo(id = graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

private fun NavHostController.findOrCreateRoute(screen: Screen): String {
    val destinationRoute: String = screen.route
    val node: NavDestination? = graph.findNode(destinationRoute)
    if (node == null) addScreen(screen)
    return destinationRoute
}

private fun NavHostController.addScreen(screen: Screen) {
    val navDestination = ComposeNavigator.Destination(
        navigator = navigatorProvider[ComposeNavigator::class],
        content = { screen.screenContent.invoke() }
    ).apply {
        route = screen.route
    }
    graph.addDestination(navDestination)
}
