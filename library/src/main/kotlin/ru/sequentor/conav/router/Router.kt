package ru.sequentor.conav.router

import kotlinx.coroutines.flow.Flow
import ru.sequentor.conav.screen.Screen

interface Router {
    val commandsFlow: Flow<RouterCommand>

    fun navigate(screen: Screen)
    fun back()
    fun backTo(screen: Screen)
    fun replace(screen: Screen)
    fun popUpTo(screen: Screen)
}
