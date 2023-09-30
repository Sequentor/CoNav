package ru.sequentor.composenavigation.feature.feature_three.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sequentor.composenavigation.core.ui.component.ComposeNavigationTopAppBar
import ru.sequentor.composenavigation.core.ui.component.NavigationIcon
import ru.sequentor.composenavigation.feature.feature_three.ui.state.FeatureThreeViewState

@Composable
internal fun FeatureThreeRoute(
    modifier: Modifier = Modifier,
    viewModel: FeatureThreeViewModel = hiltViewModel(),
) {
    val featureThreeViewState: FeatureThreeViewState by viewModel.featureThreeViewState.collectAsStateWithLifecycle()

    FeatureThreeScreen(
        modifier = modifier,
        featureThreeViewState = featureThreeViewState,
        onButtonClick = viewModel::onButtonClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeatureThreeScreen(
    modifier: Modifier = Modifier,
    featureThreeViewState: FeatureThreeViewState,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ComposeNavigationTopAppBar(
            titleRes = R.string.feature_three,
            navigationIcon = NavigationIcon.None,
            actions = { /* no-op */ }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Counter: ${featureThreeViewState.count}")
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onButtonClick) {
                Text("Multiply")
            }
        }
    }
}
