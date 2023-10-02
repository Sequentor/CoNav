package ru.sequentor.conav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.destination.Screen
import ru.sequentor.conav.destination.route
import ru.sequentor.conav.ext.addStartScreen
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
    fun init(startScreen: Screen) {
        val navController: NavHostController = rememberNavController()
        SetNavHost(navController = navController, startScreen = startScreen)
        SetRouter(navController = navController)
    }

    fun getRouter() = router

    @Composable
    private fun SetNavHost(
        navController: NavHostController,
        startScreen: Screen,
    ) {
        NavHost(
            navController = navController,
            startDestination = startScreen.route,
            modifier = Modifier,
        ) {
            addStartScreen(startScreen)
        }
    }

    @Composable
    private fun SetRouter(navController: NavHostController) {
        router.commandsFlow.AsLaunchedEffect(key = navController) { routerCommand: RouterCommand ->
            when (routerCommand) {
                is RouterCommand.Navigate -> navController.navigate(routerCommand.screen)
                is RouterCommand.Back -> navController.back()
                is RouterCommand.BackTo -> navController.backTo(routerCommand.screen)
                is RouterCommand.Replace -> navController.replace(routerCommand.screen)
                is RouterCommand.PopUpTo -> navController.popUpTo(routerCommand.screen)
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
