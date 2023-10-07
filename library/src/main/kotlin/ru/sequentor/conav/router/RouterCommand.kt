package ru.sequentor.conav.router

import ru.sequentor.conav.destination.Destination

sealed interface RouterCommand {
    data class Navigate(val destination: Destination) : RouterCommand
    data object Back : RouterCommand
    data class BackTo(val destination: Destination) : RouterCommand
    data class Replace(val destination: Destination) : RouterCommand
    data class PopUpTo(val destination: Destination) : RouterCommand
}
