package ru.sequentor.composenavigation.core.common

import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.Locale

/**
 * Инициализация логирования с помощью [Timber] для debug-сборки.
 */
fun initTimber() = Timber.plant(CustomDebugTree())

private class CustomDebugTree : DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String =
        "[%s:%s] %s".format(
            locale = Locale("ru", "RU"),
            element.methodName,
            element.lineNumber,
            super.createStackElementTag(element)
        )
}
