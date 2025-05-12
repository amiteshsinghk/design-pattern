package com.amitesh.designpattern.commandPattern

/*
* In the Command pattern, we give commands and we want our output and nothing else.
* We are not bothered about who will do our operation to give the desired result.
* All we want is our things to be done at the right time.
* */
class Light {
    fun turnOn() {
        println("Light is turned on")
    }

    fun turnOff() {
        println("Light is turned off")
    }
}

interface Command{
    fun execute()
}

class LightOnCommand(val light: Light): Command{
    override fun execute() {
        light.turnOn()
    }
}

class LightOffCommand(val light: Light): Command {
    override fun execute() {
        light.turnOff()
    }
}

class RemoteControl(){
      private var command : Command? = null

    fun setCommand(command : Command){
        this.command = command
    }

    fun pressButton(){
        command?.execute()
    }

}

fun main(){
    var remoteControl: RemoteControl = RemoteControl()
    var light : Light = Light()
    var lightOnCommand: Command = LightOnCommand(light)
    var lightOffCommand: Command = LightOffCommand(light)

    remoteControl.setCommand(lightOnCommand)
    remoteControl.pressButton()

    remoteControl.setCommand(lightOffCommand)
    remoteControl.pressButton()
}