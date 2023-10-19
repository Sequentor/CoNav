package ru.sequentor.sample.feature.feature_two.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.screen.Screen
import ru.sequentor.sample.feature.feature_two.FeatureTwoRoute

@Parcelize
class FeatureTwoScreen : Screen() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureTwoRoute() }
}
