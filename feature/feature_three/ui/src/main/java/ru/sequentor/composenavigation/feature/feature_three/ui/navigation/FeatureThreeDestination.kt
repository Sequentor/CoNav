package ru.sequentor.composenavigation.feature.feature_three.ui.navigation

import androidx.compose.runtime.Composable
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_three.ui.FeatureThreeRoute

object FeatureThreeDestination : Destination() {

    override val route: String = "feature_three_route"

    override val screenContent: @Composable () -> Unit = { FeatureThreeRoute() }
}

