package com.amitesh.designpattern.facadePattern

class Light() {
    fun turnOn() = println("Light turn On.")
    fun turnOff() = println("Light turn Off.")
}

class Projectile() {

    fun startProjectile() = println("Projectile Started.")
    fun stopProjectile() = println(" Projectile Stopped.")
}

class StartSoundSystem() {
    fun setSoundSystem() = println("Sound System Set.")
    fun startSoundSystem() = println("Sound System Started.")
    fun stopSoundSystem() = println("Sound System Stopped.")
}

class StreamingPlayer() {
    fun start() = println("Player Started.")
    fun stop() = println("Player Stopped.")
}

class HomeTheatreFacade(
    val light: Light,
    val projectile: Projectile,
    val soundSystem: StartSoundSystem,
    val streamingPlayer: StreamingPlayer) {

    fun watchMovie() {
        light.turnOn()
        projectile.startProjectile()
        soundSystem.setSoundSystem()
        soundSystem.startSoundSystem()
        streamingPlayer.start()

    }

    fun endMovie() {
        light.turnOff()
        projectile.stopProjectile()
        soundSystem.stopSoundSystem()
        streamingPlayer.stop()
    }
}

fun main(){
    val light = Light()
    val projectile = Projectile()
    val soundSystem = StartSoundSystem()
    val streamingPlayer = StreamingPlayer()
    val homeTheatre =HomeTheatreFacade(
        light =light,
        projectile = projectile,
        soundSystem = soundSystem,
        streamingPlayer = streamingPlayer
    )

    homeTheatre.watchMovie()
    println("Movie Started..... Now Stopping")
    homeTheatre.endMovie()
}