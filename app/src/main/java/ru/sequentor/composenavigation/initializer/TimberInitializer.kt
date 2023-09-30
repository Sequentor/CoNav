package ru.sequentor.composenavigation.initializer

import android.content.Context
import androidx.startup.Initializer
import ru.sequentor.composenavigation.core.common.initTimber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) = initTimber()
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
