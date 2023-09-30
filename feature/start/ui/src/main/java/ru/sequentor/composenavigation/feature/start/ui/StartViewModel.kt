package ru.sequentor.composenavigation.feature.start.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sequentor.composenavigation.core.navigation.router.Router
import javax.inject.Inject

@HiltViewModel
internal class StartViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    private val _startViewState = MutableStateFlow(StartViewState())
    val startViewState: StateFlow<StartViewState> = _startViewState

    fun onNavigationBarClick(route: String) {
         router.popUpTo(route)
    }
}
