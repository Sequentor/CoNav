package ru.sequentor.composenavigation.feature.feature_four.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.composenavigation.feature.feature_four.ui.state.FeatureFourViewState
import javax.inject.Inject

@HiltViewModel
internal class FeatureFourViewModel @Inject constructor() : ViewModel() {

    private val _featureFourViewState = MutableStateFlow(FeatureFourViewState())
    val featureFourViewState: StateFlow<FeatureFourViewState> = _featureFourViewState

    fun onButtonClick() {
        val count: Int = if (featureFourViewState.value.count > 0) featureFourViewState.value.count else Int.MAX_VALUE
        _featureFourViewState.update { it.copy(count = count / 2) }
    }
}
