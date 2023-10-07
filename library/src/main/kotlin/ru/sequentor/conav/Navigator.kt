package ru.sequentor.conav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.destination.route
import ru.sequentor.conav.ext.addStartDestination
import ru.sequentor.conav.ext.back
import ru.sequentor.conav.ext.backTo
import ru.sequentor.conav.ext.navigate
import ru.sequentor.conav.ext.popUpTo
import ru.sequentor.conav.ext.replace
import ru.sequentor.conav.router.ChannelRouter
import ru.sequentor.conav.router.Router
import ru.sequentor.conav.router.RouterCommand

class Navigator {

    private val router: Router = ChannelRouter()

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
            startDestination = startDestination.route,
            modifier = Modifier,
        ) {
            addStartDestination(startDestination)
        }
    }

    @Composable
    private fun SetRouter(navController: NavHostController) {
        router.commandsFlow.AsLaunchedEffect(key = navController) { routerCommand: RouterCommand ->
            when (routerCommand) {
                is RouterCommand.Navigate -> navController.navigate(routerCommand.destination)
                is RouterCommand.Back -> navController.back()
                is RouterCommand.BackTo -> navController.backTo(routerCommand.destination)
                is RouterCommand.Replace -> navController.replace(routerCommand.destination)
                is RouterCommand.PopUpTo -> navController.popUpTo(routerCommand.destination)
            }
        }
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
