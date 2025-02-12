package ru.sequentor.conav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.ext.addDestination
import ru.sequentor.conav.ext.back
import ru.sequentor.conav.ext.backTo
import ru.sequentor.conav.ext.bottom
import ru.sequentor.conav.ext.forward
import ru.sequentor.conav.ext.replace
import ru.sequentor.conav.ext.root
import ru.sequentor.conav.router.Back
import ru.sequentor.conav.router.BackTo
import ru.sequentor.conav.router.Bottom
import ru.sequentor.conav.router.Forward
import ru.sequentor.conav.router.Replace
import ru.sequentor.conav.router.Root
import ru.sequentor.conav.router.Router
import ru.sequentor.conav.router.RouterCommand
import ru.sequentor.conav.screen.Destination
import ru.sequentor.conav.screen.route

class Navigator(val router: Router) {

    @Composable
    operator fun invoke(
        navController: NavHostController = rememberNavController(),
        startDestination: Destination,
        modifier: Modifier = Modifier,
        contentAlignment: Alignment = Alignment.TopStart,
        enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
            fadeIn(animationSpec = tween(300))
        },
        exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
            fadeOut(animationSpec = tween(300))
        },
        popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
        popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
        sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
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
        navController: NavHostController,
        startDestination: Destination,
        modifier: Modifier = Modifier,
        contentAlignment: Alignment,
        enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition),
        exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition),
        popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition),
        popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition),
        sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)?,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = modifier,
            contentAlignment = contentAlignment,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            sizeTransform = sizeTransform,
        ) {
            addDestination(startDestination)
        }
    }

    @Composable
    private fun Router(navController: NavHostController) {
        router.commandsFlow.AsLaunchedEffect(key = navController) { routerCommand: RouterCommand ->
            when (routerCommand) {
                is Back -> navController.back()
                is Root -> navController.root(routerCommand.destination)
                is Forward -> navController.forward(routerCommand.destination)
                is Replace -> navController.replace(routerCommand.destination)
                is BackTo -> navController.backTo(routerCommand.destinationKey)
                is Bottom -> navController.bottom(routerCommand.destination)
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
