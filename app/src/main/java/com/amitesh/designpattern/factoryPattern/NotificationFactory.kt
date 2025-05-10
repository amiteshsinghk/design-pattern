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

interface NotificationService {
    fun notifyService(): String
}

class EmailNotification : NotificationService {
    override fun notifyService(): String {
        return "Email Notification"
    }
}

class SMSNotification : NotificationService {
    override fun notifyService(): String {
        return "SMS Notification"
    }
}

class NotificationFactory() {
    enum class NotificationType {
        EMAIL, SMS
    }

    fun createNotification(notificationType: NotificationType): NotificationService {
        return when (notificationType) {
            NotificationType.EMAIL -> EmailNotification()
            NotificationType.SMS -> SMSNotification()
        }
    }

}

fun main(){
    var notification = NotificationFactory()
    var email = notification.createNotification(NotificationFactory.NotificationType.EMAIL)
    println(email.notifyService())
}