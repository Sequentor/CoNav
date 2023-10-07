package ru.sequentor.sample.feature.feature_one.first.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.destination.Destination
import ru.sequentor.sample.feature.feature_one.first.FeatureOneFirstRoute

@Parcelize
class FeatureOneFirstDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureOneFirstRoute() }
}
