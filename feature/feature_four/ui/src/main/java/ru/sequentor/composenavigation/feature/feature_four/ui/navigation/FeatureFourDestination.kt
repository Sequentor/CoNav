package ru.sequentor.composenavigation.feature.feature_four.ui.navigation

import androidx.compose.runtime.Composable
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_four.ui.FeatureFourRoute

object FeatureFourDestination : Destination() {

    override val route: String = "feature_four_route"

    override val screenContent: @Composable () -> Unit = { FeatureFourRoute() }
}
