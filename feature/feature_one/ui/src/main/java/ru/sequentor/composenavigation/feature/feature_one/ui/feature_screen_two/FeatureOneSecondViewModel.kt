package ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.composenavigation.core.navigation.router.Router
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two.navigation.FeatureOneSecondDestination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two.state.FeatureOneSecondViewState
import javax.inject.Inject

@HiltViewModel
internal class FeatureOneSecondViewModel @Inject constructor(
    private val router: Router,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = FeatureOneSecondDestination.InternalArgs(savedStateHandle)

    private val _featureOneSecondViewState = MutableStateFlow(FeatureOneSecondViewState())
    val featureOneSecondViewState: StateFlow<FeatureOneSecondViewState> = _featureOneSecondViewState

    init {
        _featureOneSecondViewState.update { it.copy(count = args.count, result = args.result) }
    }

    fun onNavigationClick() {
        router.back()
    }
}
