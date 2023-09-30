@file:OptIn(ExperimentalMaterial3Api::class)

package ru.sequentor.composenavigation.core.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun ComposeNavigationTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    navigationIcon: NavigationIcon = NavigationIcon.None,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets.Companion.displayCutout,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        title = {
            Text(
                text = stringResource(id = titleRes),
            )
        },
        navigationIcon = {
            if (navigationIcon != NavigationIcon.None) {
                IconButton(onClick = onNavigationClick) {
                    val icon = when (navigationIcon) {
                        NavigationIcon.Back -> Icons.Default.ArrowBack
                        else -> error("No more icons can set as navigationIcon")
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = actions,
        scrollBehavior = scrollBehavior,
    )
}

enum class NavigationIcon {
    Back, None,
}
