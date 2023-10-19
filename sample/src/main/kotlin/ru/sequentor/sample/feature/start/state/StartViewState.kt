package ru.sequentor.sample.feature.start.state

import ru.sequentor.sample.R
import ru.sequentor.sample.feature.feature_four.navigation.FeatureFourScreen
import ru.sequentor.sample.feature.feature_one.first.navigation.FeatureOneFirstScreen
import ru.sequentor.sample.feature.feature_three.navigation.FeatureThreeScreen
import ru.sequentor.sample.feature.feature_two.navigation.FeatureTwoScreen
import ru.sequentor.sample.ui.component.NavigationItem

internal data class StartViewState(
    val navigationItems: List<NavigationItem> = buildNavigationItems(),
)

private fun buildNavigationItems(): List<NavigationItem> = listOf(
    NavigationItem(
        route = FeatureOneFirstScreen::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_1,
        initialRoute = true,
    ),
    NavigationItem(
        route = FeatureTwoScreen::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_2,
    ),
    NavigationItem(
        route = FeatureThreeScreen::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_3,
    ),
    NavigationItem(
        route = FeatureFourScreen::class.java.canonicalName,
        iconRes = R.drawable.ic_icon_4,
    ),
)
