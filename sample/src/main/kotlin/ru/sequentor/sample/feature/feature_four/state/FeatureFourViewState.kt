package ru.sequentor.sample.feature.feature_four.state

import androidx.compose.runtime.Immutable

@Immutable
internal data class FeatureFourViewState(
    val count: Int = Int.MAX_VALUE,
)
