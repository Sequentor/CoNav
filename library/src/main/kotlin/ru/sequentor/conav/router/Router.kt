package ru.sequentor.conav.router

import ru.sequentor.conav.screen.Destination

open class Router : BaseRouter() {

    fun back() {
        executeRouterCommand(Back)
    }

    fun navigateTo(destination: Destination) {
        executeRouterCommand(Forward(destination))
    }

    fun backTo(destinationKey: String?) {
        executeRouterCommand(BackTo(destinationKey))
    }

    fun replace(destination: Destination) {
        executeRouterCommand(Replace(destination))
    }

    fun newRootScreen(destination: Destination) {
        executeRouterCommand(Root(destination))
    }

    fun bottom(destination: Destination) {
        executeRouterCommand(Bottom(destination))
    }
}
