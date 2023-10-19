package ru.sequentor.conav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.screen.Screen
import ru.sequentor.conav.screen.route
import ru.sequentor.conav.ext.addDestination
import ru.sequentor.conav.ext.back
import ru.sequentor.conav.ext.backTo
import ru.sequentor.conav.ext.navigate
import ru.sequentor.conav.ext.popUpTo
import ru.sequentor.conav.ext.replace
import ru.sequentor.conav.router.Back
import ru.sequentor.conav.router.BackTo
import ru.sequentor.conav.router.ChannelRouter
import ru.sequentor.conav.router.Navigate
import ru.sequentor.conav.router.PopUpTo
import ru.sequentor.conav.router.Replace
import ru.sequentor.conav.router.Router
import ru.sequentor.conav.router.RouterCommand

class Navigator {

    private val router: Router = ChannelRouter()

    @Composable
    operator fun invoke(startScreen: Screen) {
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
            addDestination(startScreen)
        }
    }

    @Composable
    private fun SetRouter(navController: NavHostController) {
        router.commandsFlow.AsLaunchedEffect(key = navController) { routerCommand: RouterCommand ->
            when (routerCommand) {
                is Navigate -> navController.navigate(routerCommand.screen)
                is Back -> navController.back()
                is BackTo -> navController.backTo(routerCommand.screen)
                is Replace -> navController.replace(routerCommand.screen)
                is PopUpTo -> navController.popUpTo(routerCommand.screen)
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
