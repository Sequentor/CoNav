package ru.sequentor.composenavigation.core.navigation.router

import android.os.Bundle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

internal class ChannelRouter(
    commandsBufferSize: Int = DEFAULT_COMMANDS_BUFFER_SIZE,
) : Router {

    private val commandsChannel = Channel<RouterCommand>(capacity = commandsBufferSize)

    override val commandsFlow: Flow<RouterCommand>
        get() = commandsChannel.receiveAsFlow()

    override fun navigate(route: String, bundle: Bundle?) {
        commandsChannel.trySend(RouterCommand.Navigate(route, bundle))
    }

    override fun back() {
        commandsChannel.trySend(RouterCommand.Back)
    }

    override fun backTo(route: String) {
        commandsChannel.trySend(RouterCommand.BackTo(route))
    }

    override fun replace(route: String) {
        commandsChannel.trySend(RouterCommand.Replace(route))
    }

    override fun popUpTo(route: String) {
        commandsChannel.trySend(RouterCommand.PopUpTo(route))
    }

    companion object {
        private const val DEFAULT_COMMANDS_BUFFER_SIZE = 64
    }
}
