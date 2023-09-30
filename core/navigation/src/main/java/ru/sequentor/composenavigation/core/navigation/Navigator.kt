package ru.sequentor.composenavigation.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import ru.sequentor.composenavigation.core.navigation.router.Router
import ru.sequentor.composenavigation.core.navigation.router.RouterCommand
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class Navigator @Inject constructor(
    private val router: Router,
) {

    @Composable
    operator fun invoke(
        startDestination: String,
        builder: NavGraphBuilder.() -> Unit,
    ) {
        val navController: NavHostController = rememberNavController()
        SetNavHost(navController = navController, startDestination = startDestination, builder = builder)
        SetRouter(navController = navController)
    }

    @Composable
    private fun SetNavHost(
        navController: NavHostController,
        startDestination: String,
        builder: NavGraphBuilder.() -> Unit,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier,
            builder = builder
        )
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
        val node: NavDestination? = navController.graph.findNode(routerCommand.route)
        if (node != null) navController.navigate(node.id, routerCommand.bundle)
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

    private fun popUpTo(
        navController: NavHostController,
        routerCommand: RouterCommand.PopUpTo,
    ) = navController.navigate(routerCommand.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    private fun backTo(
        navController: NavHostController,
        routerCommand: RouterCommand.BackTo,
    ) = navController.popBackStack(
        routerCommand.route,
        inclusive = false,
        saveState = false
    )

    @Composable
    internal fun <T> Flow<T>.AsLaunchedEffect(key: Any, effect: (T) -> Unit) {
        LaunchedEffect(key, this) {
            collect { value ->
                effect(value)
            }
        }
    }
}
