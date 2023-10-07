package ru.sequentor.sample.feature.feature_one.first

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.feature_one.first.state.FeatureOneViewState
import ru.sequentor.sample.feature.feature_one.second.navigation.FeatureOneSecondDestination
import javax.inject.Inject

@HiltViewModel
internal class FeatureOneViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _featureOneViewState = MutableStateFlow(FeatureOneViewState())
    val featureOneViewState: StateFlow<FeatureOneViewState> = _featureOneViewState

    fun onButtonClick() {
        _featureOneViewState.update { it.copy(count = featureOneViewState.value.count + 1) }
    }

    fun onNavigateSecondScreen() {
        router.navigate(FeatureOneSecondDestination(featureOneViewState.value.count, Result.success("Success")))
    }
}
