package com.amitesh.designpattern.factoryPattern


/*
* Factory takes care of all the object creational logic. In this pattern, a factory class controls
* which object to instantiate. Factory pattern comes in handy when dealing with many common objects.
* You can use it where you might not want to specify a concrete class.
*
*
* Factory Pattern is a creational design pattern that provides an interface for creating objects in
* a superclass but allows subclasses to alter the type of objects that will be created.
* */

// Create a factory Dialog which return different dialog instance for different error


interface ErrorDialog {
    fun createDialog(): String
}

class NetworkDialog : ErrorDialog {
    override fun createDialog(): String {
        return "This is network Dialog."
    }
}

class ServerErrorDialog : ErrorDialog {
    override fun createDialog(): String {
        return "This is Server Error Dialog."
    }
}

class LowSpeedDialog : ErrorDialog {
    override fun createDialog(): String {
        return "This is Server Error Dialog."
    }
}


class FactoryPattern {

    enum class ErrorDialogType {
        NETWORK, SERVER, LOW_SPEED
    }

    fun createDialogFactory(errorDialogType: ErrorDialogType): ErrorDialog {
        return when (errorDialogType) {
            ErrorDialogType.NETWORK -> NetworkDialog()
            ErrorDialogType.SERVER -> ServerErrorDialog()
            ErrorDialogType.LOW_SPEED -> LowSpeedDialog()
        }
    }
}

fun main(){
    var v = FactoryPattern()
    var network = v.createDialogFactory(FactoryPattern.ErrorDialogType.NETWORK)
    println(network.createDialog())

}