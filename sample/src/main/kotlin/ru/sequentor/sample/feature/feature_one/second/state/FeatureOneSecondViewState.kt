package ru.sequentor.sample.feature.feature_one.second.state

import androidx.compose.runtime.Immutable
import java.io.FileNotFoundException

@Immutable
internal data class FeatureOneSecondViewState(
    val count: Int = -1,
    val result: Result<String> = Result.failure(FileNotFoundException())
)
