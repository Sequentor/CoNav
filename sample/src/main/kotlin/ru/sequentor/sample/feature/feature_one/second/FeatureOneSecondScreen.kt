package ru.sequentor.sample.feature.feature_one.second

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import ru.sequentor.sample.R
import ru.sequentor.sample.feature.feature_one.second.state.FeatureOneSecondViewState
import ru.sequentor.sample.ui.component.ComposeNavigationTopAppBar
import ru.sequentor.sample.ui.component.NavigationIcon

@Composable
internal fun FeatureOneSecondRoute(
    modifier: Modifier = Modifier,
    viewModel: FeatureOneSecondViewModel = hiltViewModel(),
) {
    val featureOneSecondViewState: FeatureOneSecondViewState by viewModel.featureOneSecondViewState.collectAsStateWithLifecycle()
    FeatureOneSecondScreen(
        modifier = modifier,
        featureOneSecondViewState = featureOneSecondViewState,
        onNavigationClick = viewModel::onNavigationClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeatureOneSecondScreen(
    modifier: Modifier = Modifier,
    featureOneSecondViewState: FeatureOneSecondViewState,
    onNavigationClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ComposeNavigationTopAppBar(
            titleRes = R.string.feature_one_second,
            navigationIcon = NavigationIcon.Back,
            onNavigationClick = onNavigationClick,
            actions = { /* no-op */ }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "count: ${featureOneSecondViewState.count}")
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "result: ${featureOneSecondViewState.result}")
        }
    }
}
