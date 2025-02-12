package ru.sequentor.conav.router

import ru.sequentor.conav.screen.Destination

interface RouterCommand

internal data object Back : RouterCommand

internal data class Root(val destination: Destination) : RouterCommand

internal data class Forward(val destination: Destination) : RouterCommand

internal data class Replace(val destination: Destination) : RouterCommand

internal data class BackTo(val destinationKey: String?) : RouterCommand

internal data class Bottom(val destination: Destination) : RouterCommand