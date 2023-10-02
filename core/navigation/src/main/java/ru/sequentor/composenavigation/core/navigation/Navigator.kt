package ru.sequentor.composenavigation.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.core.navigation.router.Router
import ru.sequentor.composenavigation.core.navigation.router.RouterCommand
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class Navigator @Inject constructor(
    private val router: Router,
) {

    @Composable
    operator fun invoke(startDestination: Destination) {
        val navController: NavHostController = rememberNavController()
        SetNavHost(navController = navController, startDestination = startDestination)
        SetRouter(navController = navController)
    }

    fun getRouter() = router

    @Composable
    private fun SetNavHost(
        navController: NavHostController,
        startDestination: Destination,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination.toString(),
            modifier = Modifier,
        ) {
            startDestination.addDestination(this)
        }
    }

    @Composable
    private fun SetRouter(navController: NavHostController) {
        router.commandsFlow.AsLaunchedEffect(key = navController) { routerCommand: RouterCommand ->
            when (routerCommand) {
                is RouterCommand.Navigate -> navigate(navController, routerCommand)
                is RouterCommand.Back -> back(navController)
                is RouterCommand.BackTo -> backTo(navController, routerCommand)
                is RouterCommand.Replace -> replace(navController, routerCommand)
                is RouterCommand.PopUpTo -> popUpTo(navController, routerCommand)
            }
        }
    }

    private fun navigate(
        navController: NavHostController,
        routerCommand: RouterCommand.Navigate,
    ) {
        val route: String = findOrCreateRoute(navController, routerCommand.destination)
        val node: NavDestination? = navController.graph.findNode(route)
        if (node != null) {
            navController.navigate(
                resId = node.id,
                args = bundleOf(routerCommand.destination::class.toString() to routerCommand.destination)
            )
        }
    }

    private fun back(
        navController: NavHostController,
    ) = navController.popBackStack()

    private fun replace(
        navController: NavHostController,
        routerCommand: RouterCommand.Replace,
    ) = navController.navigate(routerCommand.route) {
        val currentRoute = navController.currentBackStackEntry
            ?.destination
            ?.route
        if (currentRoute != null) {
            popUpTo(currentRoute) { inclusive = true }
        }
    }

    private fun addDestination(navController: NavHostController, destination: Destination) {
        val navDestination = ComposeNavigator.Destination(
            navigator = navController.navigatorProvider[ComposeNavigator::class],
            content = { destination.screenContent.invoke() }
        ).apply {
            route = destination.toString()
        }
        navController.graph.addDestination(navDestination)
    }

    private fun popUpTo(
        navController: NavHostController,
        routerCommand: RouterCommand.PopUpTo,
    ) {
        val route: String = findOrCreateRoute(navController, routerCommand.destination)
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    private fun backTo(
        navController: NavHostController,
        routerCommand: RouterCommand.BackTo,
    ) = navController.popBackStack(
        routerCommand.route,
        inclusive = false,
        saveState = false
    )

    private fun findOrCreateRoute(navController: NavHostController, destination: Destination): String {
        val destinationRoute: String = destination.toString()
        val node: NavDestination? = navController.graph.findNode(destinationRoute)
        if (node == null) addDestination(navController, destination)
        return destinationRoute
    }

    @Composable
    internal fun <T> Flow<T>.AsLaunchedEffect(key: Any, effect: (T) -> Unit) {
        LaunchedEffect(key, this) {
            collect { value ->
                effect(value)
            }
        }
    }
}
