package ru.sequentor.composenavigation.core.navigation.router

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import ru.sequentor.composenavigation.core.navigation.destination.Destination

internal class ChannelRouter(
    commandsBufferSize: Int = DEFAULT_COMMANDS_BUFFER_SIZE,
) : Router {

    private val commandsChannel = Channel<RouterCommand>(capacity = commandsBufferSize)

    override val commandsFlow: Flow<RouterCommand>
        get() = commandsChannel.receiveAsFlow()

    override fun navigate(destination: Destination) {
        commandsChannel.trySend(RouterCommand.Navigate(destination))
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

    override fun popUpTo(destination: Destination) {
        commandsChannel.trySend(RouterCommand.PopUpTo(destination))
    }

    companion object {
        private const val DEFAULT_COMMANDS_BUFFER_SIZE = 64
    }
}
