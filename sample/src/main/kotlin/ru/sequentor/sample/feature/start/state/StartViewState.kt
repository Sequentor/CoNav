package ru.sequentor.sample.feature.start.state

import ru.sequentor.sample.R
import ru.sequentor.sample.feature.feature_four.navigation.FeatureFourDestination
import ru.sequentor.sample.feature.feature_one.first.navigation.FeatureOneFirstDestination
import ru.sequentor.sample.feature.feature_three.navigation.FeatureThreeDestination
import ru.sequentor.sample.feature.feature_two.navigation.FeatureTwoDestination
import ru.sequentor.sample.ui.component.NavigationItem

internal data class StartViewState(
    val navigationItems: List<NavigationItem> = buildNavigationItems(),
)

private fun buildNavigationItems(): List<NavigationItem> = listOf(
    NavigationItem(
        route = FeatureOneFirstDestination::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_1,
        initialRoute = true,
    ),
    NavigationItem(
        route = FeatureTwoDestination::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_2,
    ),
    NavigationItem(
        route = FeatureThreeDestination::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_3,
    ),
    NavigationItem(
        route = FeatureFourDestination::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_4,
    ),
)
