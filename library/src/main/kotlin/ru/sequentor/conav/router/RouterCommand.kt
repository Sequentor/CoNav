package ru.sequentor.conav.router

import ru.sequentor.conav.destination.Destination

interface RouterCommand

internal data object Back : RouterCommand

internal data class BackTo(val key: String) : RouterCommand

internal data class Forward(val destination: Destination) : RouterCommand

internal data class Replace(val destination: Destination) : RouterCommand

internal data class PopUpTo(val destination: Destination) : RouterCommand

internal data class Root(val destination: Destination) : RouterCommand

internal data class RootGraph(val destination: Destination) : RouterCommand