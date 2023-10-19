package ru.sequentor.conav.router

import ru.sequentor.conav.screen.Screen

interface RouterCommand

data class Navigate(val screen: Screen) : RouterCommand
data object Back : RouterCommand
data class BackTo(val screen: Screen) : RouterCommand
data class Replace(val screen: Screen) : RouterCommand
data class PopUpTo(val screen: Screen) : RouterCommand

