package ru.sequentor.sample.feature.start

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Newspaper
import ru.sequentor.sample.feature.bottom.favorite.FavoriteDestination
import ru.sequentor.sample.feature.bottom.home.HomeDestination
import ru.sequentor.sample.feature.bottom.music.MusicDestination
import ru.sequentor.sample.feature.bottom.news.NewsDestination
import ru.sequentor.sample.feature.start.NavDestination.FavoriteNavDestination
import ru.sequentor.sample.feature.start.NavDestination.HomeNavDestination
import ru.sequentor.sample.feature.start.NavDestination.MusicNavDestination
import ru.sequentor.sample.feature.start.NavDestination.NewsNavDestination
import ru.sequentor.sample.ui.component.NavigationItem

internal sealed interface NavDestination {
    object HomeNavDestination : NavDestination
    object NewsNavDestination : NavDestination
    object MusicNavDestination : NavDestination
    object FavoriteNavDestination : NavDestination
}

internal fun NavDestination.toDestination() = when (this) {
    HomeNavDestination -> HomeDestination()
    NewsNavDestination -> NewsDestination()
    MusicNavDestination -> MusicDestination()
    FavoriteNavDestination -> FavoriteDestination()
}

internal data class StartViewState(val navigationItems: List<NavigationItem> = buildNavigationItems())

private fun buildNavigationItems(): List<NavigationItem> = listOf(
    NavigationItem(
        navDestination = HomeNavDestination,
        imageVector = Icons.Default.Home,
        startDestination = true
    ),
    NavigationItem(
        navDestination = NewsNavDestination,
        imageVector = Icons.Default.Newspaper,
    ),
    NavigationItem(
        navDestination = MusicNavDestination,
        imageVector = Icons.Default.MusicNote,
    ),
    NavigationItem(
        navDestination = FavoriteNavDestination,
        imageVector = Icons.Default.Favorite,
    ),
)