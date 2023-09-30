package ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two.FeatureOneSecondRoute

object FeatureOneSecondDestination : Destination() {

    internal const val COUNT_ARG = "count"
    internal const val RESULT_ARG = "result"

    override val route: String = "second_feature_route"

    override val screenContent: @Composable () -> Unit = { FeatureOneSecondRoute() }

    fun bundle(
        count: Int,
        result: Result<String>,
    ): Bundle = bundleOf(
        COUNT_ARG to count,
        RESULT_ARG to result,
    )

    internal data class InternalArgs(
        val count: Int,
        val result: Result<String>,
    ) {
        constructor(savedStateHandle: SavedStateHandle) : this(
            count = requireNotNull(savedStateHandle[COUNT_ARG]),
            result = requireNotNull(savedStateHandle[RESULT_ARG]),
        )
    }
}
