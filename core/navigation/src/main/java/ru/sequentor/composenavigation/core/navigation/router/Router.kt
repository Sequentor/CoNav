package ru.sequentor.composenavigation.core.navigation.router

import kotlinx.coroutines.flow.Flow
import ru.sequentor.composenavigation.core.navigation.destination.Destination

interface Router {
    val commandsFlow: Flow<RouterCommand>

    fun navigate(destination: Destination)
    fun back()
    fun backTo(route: String)
    fun replace(route: String)
    fun popUpTo(destination: Destination)
}

