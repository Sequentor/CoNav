package ru.sequentor.composenavigation.feature.start.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sequentor.composenavigation.core.navigation.Navigator
import ru.sequentor.composenavigation.core.ui.component.ComposeNavigationNavigationBarCornered
import ru.sequentor.composenavigation.core.ui.component.ComposeNavigationScaffold
import ru.sequentor.composenavigation.core.ui.theme.ComposeNavigationTheme
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.navigation.FeatureOneFirstDestination

@Composable
internal fun StartScreen(
    navigator: Navigator,
    startViewModel: StartViewModel = hiltViewModel(),
) {
    val startViewState: StartViewState by startViewModel.startViewState.collectAsStateWithLifecycle()
    ComposeNavigationTheme {
        ComposeNavigationScaffold(
            modifier = Modifier.navigationBarsPadding(),
            bottomBar = {
                ComposeNavigationNavigationBarCornered(
                    navigationItems = startViewState.navigationItems,
                    onNavigationBarClick = startViewModel::onNavigationBarClick
                )
            },
            content = {
                navigator(startDestination = FeatureOneFirstDestination())
            }
        )
    }
}

//private fun NavGraphBuilder.registerScreens() {
//    FeatureOneFirstDestination.composable(this)
//    FeatureOneSecondDestination.composable(this)
//    FeatureTwoDestination.composable(this)
//    FeatureThreeDestination.composable(this)
//    FeatureFourDestination.composable(this)
//}
