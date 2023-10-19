package ru.sequentor.sample.feature.feature_one.second

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.screen.args
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.feature_one.second.navigation.FeatureOneSecondScreen
import ru.sequentor.sample.feature.feature_one.second.state.FeatureOneSecondViewState
import javax.inject.Inject

@HiltViewModel
internal class FeatureOneSecondViewModel @Inject constructor(
    private val router: Router,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: FeatureOneSecondScreen = savedStateHandle.args()

    private val _featureOneSecondViewState = MutableStateFlow(FeatureOneSecondViewState())
    val featureOneSecondViewState: StateFlow<FeatureOneSecondViewState> = _featureOneSecondViewState

    init {
        _featureOneSecondViewState.update { it.copy(count = args.count, result = args.result) }
    }

    fun onNavigationClick() {
        router.back()
    }
}
