@file:OptIn(ExperimentalMaterial3Api::class)

package ru.sequentor.sample.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ComposeNavigationTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: NavigationIcon = NavigationIcon.None,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets.Companion.displayCutout,
        title = { Text(text = title) },
        navigationIcon = {
            if (navigationIcon != NavigationIcon.None) {
                IconButton(onClick = onNavigationClick) {
                    val icon = when (navigationIcon) {
                        NavigationIcon.Back -> Icons.AutoMirrored.Filled.ArrowBack
                        else -> error("No more icons can set as navigationIcon")
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = MaterialTheme.colorScheme.background),
        actions = actions,
        scrollBehavior = scrollBehavior,
    )
}

enum class NavigationIcon {
    Back, None,
}
