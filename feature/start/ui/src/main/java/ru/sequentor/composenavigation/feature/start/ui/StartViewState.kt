package ru.sequentor.composenavigation.feature.start.ui

import ru.sequentor.composenavigation.core.ui.R
import ru.sequentor.composenavigation.core.ui.component.NavigationItem
import ru.sequentor.composenavigation.feature.feature_four.ui.navigation.FeatureFourDestination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.navigation.FeatureOneFirstDestination
import ru.sequentor.composenavigation.feature.feature_three.ui.navigation.FeatureThreeDestination
import ru.sequentor.composenavigation.feature.feature_two.ui.navigation.FeatureTwoDestination

internal data class StartViewState(
    val navigationItems: List<NavigationItem> = buildNavigationItems(),
) {
    fun initialRoute() = navigationItems.first { it.initialRoute }.route
}

private fun buildNavigationItems(): List<NavigationItem> = listOf(
    NavigationItem(
        route = FeatureOneFirstDestination::class.toString(),
        iconRes = R.drawable.ic_icon_1,
        initialRoute = true,
    ),
    NavigationItem(
        route = FeatureTwoDestination::class.toString(),
        iconRes = R.drawable.ic_icon_2,
    ),
    NavigationItem(
        route = FeatureThreeDestination::class.toString(),
        iconRes = R.drawable.ic_icon_3,
    ),
    NavigationItem(
        route = FeatureFourDestination::class.toString(),
        iconRes = R.drawable.ic_icon_4,
    ),
)
