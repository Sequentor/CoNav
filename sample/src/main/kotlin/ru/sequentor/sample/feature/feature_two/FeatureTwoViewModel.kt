package ru.sequentor.sample.feature.feature_two

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.sample.feature.feature_two.state.FeatureTwoViewState
import javax.inject.Inject

@HiltViewModel
internal class FeatureTwoViewModel @Inject constructor() : ViewModel() {

    private val _featureTwoViewState = MutableStateFlow(FeatureTwoViewState())
    val featureTwoViewState: StateFlow<FeatureTwoViewState> = _featureTwoViewState

    fun onButtonClick() {
        _featureTwoViewState.update { it.copy(count = featureTwoViewState.value.count - 1) }
    }
}
