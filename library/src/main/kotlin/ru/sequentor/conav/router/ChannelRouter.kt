package ru.sequentor.conav.router

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import ru.sequentor.conav.screen.Screen

internal class ChannelRouter(
    commandsBufferSize: Int = DEFAULT_COMMANDS_BUFFER_SIZE,
) : Router {

    private val commandsChannel = Channel<RouterCommand>(capacity = commandsBufferSize)
    override val commandsFlow: Flow<RouterCommand>
        get() = commandsChannel.receiveAsFlow()

    override fun navigate(screen: Screen) {
        commandsChannel.trySend(Navigate(screen))
    }

    override fun back() {
        commandsChannel.trySend(Back)
    }

    override fun backTo(screen: Screen) {
        commandsChannel.trySend(BackTo(screen))
    }

    override fun replace(screen: Screen) {
        commandsChannel.trySend(Replace(screen))
    }

    override fun popUpTo(screen: Screen) {
        commandsChannel.trySend(PopUpTo(screen))
    }

    companion object {
        private const val DEFAULT_COMMANDS_BUFFER_SIZE = 64
    }
}
