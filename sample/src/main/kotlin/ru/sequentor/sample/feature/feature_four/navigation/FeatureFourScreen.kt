package ru.sequentor.sample.feature.feature_four.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.screen.Screen
import ru.sequentor.sample.feature.feature_four.FeatureFourRoute

@Parcelize
class FeatureFourScreen : Screen() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureFourRoute() }
}
