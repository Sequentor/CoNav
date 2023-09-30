package ru.sequentor.composenavigation.core.navigation.router

import android.os.Bundle
import kotlinx.coroutines.flow.Flow

interface Router {
    val commandsFlow: Flow<RouterCommand>

    fun navigate(route: String, bundle: Bundle? = null)
    fun back()
    fun backTo(route: String)
    fun replace(route: String)
    fun popUpTo(route: String)
}

