package ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.FeatureOneFirstRoute

@Parcelize
class FeatureOneFirstDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureOneFirstRoute() }
}
