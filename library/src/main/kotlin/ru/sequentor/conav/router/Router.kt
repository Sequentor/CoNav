package ru.sequentor.conav.router

import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.destination.Destination

interface Router {
    val commandsFlow: Flow<RouterCommand>

    fun navigate(destination: Destination)
    fun back()
    fun backTo(destination: Destination)
    fun replace(destination: Destination)
    fun popUpTo(destination: Destination)
}
