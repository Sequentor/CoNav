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
import ru.sequentor.sample.ui.component.NavigationItem

internal data class StartViewState(val navigationItems: List<NavigationItem> = buildNavigationItems())

private fun buildNavigationItems(): List<NavigationItem> = listOf(
    NavigationItem(
        destination = HomeDestination,
        imageVector = Icons.Default.Home,
        startDestination = true
    ),
    NavigationItem(
        destination = NewsDestination,
        imageVector = Icons.Default.Newspaper,
    ),
    NavigationItem(
        destination = MusicDestination,
        imageVector = Icons.Default.MusicNote,
    ),
    NavigationItem(
        destination = FavoriteDestination,
        imageVector = Icons.Default.Favorite,
    ),
)