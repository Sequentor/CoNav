package ru.sequentor.conav.router

import ru.sequentor.conav.destination.Destination

class Router : BaseRouter() {

    fun back() {
        executeRouterCommand(Back)
    }

    fun backTo(key: String) {
        executeRouterCommand(BackTo(key))
    }

    fun replace(destination: Destination) {
        executeRouterCommand(Replace(destination))
    }

    fun navigateTo(destination: Destination) {
        executeRouterCommand(Forward(destination))
    }

    fun popUpTo(destination: Destination) {
        executeRouterCommand(PopUpTo(destination))
    }

    fun newRoot(destination: Destination) {
        executeRouterCommand(Root(destination))
    }

    fun newRootGraph(destination: Destination) {
        executeRouterCommand(RootGraph(destination))
    }
}