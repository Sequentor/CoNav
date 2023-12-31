package ru.sequentor.sample.feature.feature_three

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.sample.feature.feature_three.state.FeatureThreeViewState
import javax.inject.Inject

@HiltViewModel
internal class FeatureThreeViewModel @Inject constructor() : ViewModel() {

    private val _featureThreeViewState = MutableStateFlow(FeatureThreeViewState())
    val featureThreeViewState: StateFlow<FeatureThreeViewState> = _featureThreeViewState

    fun onButtonClick() {
        _featureThreeViewState.update { it.copy(count = featureThreeViewState.value.count * 2) }
    }
}
