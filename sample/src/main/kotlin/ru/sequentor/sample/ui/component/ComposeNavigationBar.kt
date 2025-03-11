package ru.sequentor.sample.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.sequentor.sample.feature.start.NavDestination
import ru.sequentor.sample.ui.theme.Green

@Composable
internal fun ComposeNavigationBarCornered(
    navigationItems: List<NavigationItem>,
    onNavigationBarClick: (navDestination: NavDestination) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            navigationItems.forEachIndexed { index, navigationItem ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = navigationItem.imageVector,
                            contentDescription = null
                        )
                    },
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        onNavigationBarClick.invoke(navigationItem.navDestination)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.White,
                        selectedIconColor = Green
                    )
                )
            }
        }
    )
}

internal data class NavigationItem(
    val navDestination: NavDestination,
    val imageVector: ImageVector,
    val startDestination: Boolean = false,
)
