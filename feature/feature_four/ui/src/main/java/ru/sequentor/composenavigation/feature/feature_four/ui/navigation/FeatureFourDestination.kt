package ru.sequentor.composenavigation.feature.feature_four.ui.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_four.ui.FeatureFourRoute

@Parcelize
class FeatureFourDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureFourRoute() }
}
