package ru.sequentor.composenavigation.core.navigation.router

import ru.sequentor.composenavigation.core.navigation.destination.Destination

sealed interface RouterCommand {
    data class Navigate(val destination: Destination) : RouterCommand
    data object Back : RouterCommand
    data class BackTo(val route: String) : RouterCommand
    data class Replace(val route: String) : RouterCommand
    data class PopUpTo(val destination: Destination) : RouterCommand
}
