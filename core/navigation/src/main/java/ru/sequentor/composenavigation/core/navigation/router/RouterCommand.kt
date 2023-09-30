package ru.sequentor.composenavigation.core.navigation.router

import android.os.Bundle

sealed interface RouterCommand {
    data class Navigate(val route: String, val bundle: Bundle? = null) : RouterCommand
    data object Back : RouterCommand
    data class BackTo(val route: String) : RouterCommand
    data class Replace(val route: String) : RouterCommand
    data class PopUpTo(val route: String) : RouterCommand
}
