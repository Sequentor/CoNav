package ru.sequentor.conav.navigator

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.ext.addDestination
import ru.sequentor.conav.ext.back
import ru.sequentor.conav.ext.backTo
import ru.sequentor.conav.ext.forward
import ru.sequentor.conav.ext.popUpTo
import ru.sequentor.conav.ext.replace
import ru.sequentor.conav.ext.root
import ru.sequentor.conav.ext.rootGraph
import ru.sequentor.conav.router.Back
import ru.sequentor.conav.router.BackTo
import ru.sequentor.conav.router.BaseRouter
import ru.sequentor.conav.router.Forward
import ru.sequentor.conav.router.PopUpTo
import ru.sequentor.conav.router.Replace
import ru.sequentor.conav.router.Root
import ru.sequentor.conav.router.RootGraph
import ru.sequentor.conav.router.RouterCommand

class Navigator(val router: BaseRouter) {

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        startDestination: Destination,
        contentAlignment: Alignment = Alignment.TopStart,
        enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
            fadeIn()
        },
        exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
            fadeOut()
        },
        popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
        popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
        sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination,
            contentAlignment = contentAlignment,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            sizeTransform = sizeTransform,
        )
        Router(navController = navController)
    }

    @Composable
    private fun NavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        startDestination: Destination,
        contentAlignment: Alignment,
        enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition),
        exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition),
        popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition),
        popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition),
        sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)?,
    ) {
        androidx.navigation.compose.NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination.key,
            contentAlignment = contentAlignment,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            sizeTransform = sizeTransform
        ) {
            addDestination(destination = startDestination)
        }
    }

    @Composable
    fun Router(navController: NavHostController) {
        router.setNavHostController(navController)
        router.commandsFlow.AsLaunchedEffect(key = navController) { routerCommand: RouterCommand ->
            when (routerCommand) {
                is Back -> navController.back()
                is BackTo -> navController.backTo(routerCommand.key)
                is Forward -> navController.forward(routerCommand.destination)
                is Replace -> navController.replace(routerCommand.destination)
                is PopUpTo -> navController.popUpTo(routerCommand.destination)
                is Root -> navController.root(routerCommand.destination)
                is RootGraph -> navController.rootGraph(routerCommand.destination)
            }
        }
    }

    @Composable
    private fun <T> Flow<T>.AsLaunchedEffect(key: Any, effect: (T) -> Unit) {
        LaunchedEffect(key, this) {
            collect { value ->
                effect(value)
            }
        }
    }
}
