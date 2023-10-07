package ru.sequentor.sample.feature.feature_four.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.destination.Destination
import ru.sequentor.sample.feature.feature_four.FeatureFourRoute

@Parcelize
class FeatureFourDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureFourRoute() }
}
