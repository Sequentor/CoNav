package ru.sequentor.conav.router

import androidx.navigation.NavHostController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

private const val DEFAULT_COMMANDS_BUFFER_SIZE = 64

abstract class BaseRouter {

    private var navHostController: NavHostController? = null

    private val commandsChannel = Channel<RouterCommand>(capacity = DEFAULT_COMMANDS_BUFFER_SIZE)

    val commandsFlow: Flow<RouterCommand>
        get() = commandsChannel.receiveAsFlow()

    fun executeRouterCommand(command: RouterCommand) {
        commandsChannel.trySend(command)
    }

    fun setNavHostController(navHostController: NavHostController) {
        this.navHostController = navHostController
    }
}