package com.amitesh.designpattern.factoryPattern

interface EventListener {
    fun sendEvent()
}

class AdobeAnalytics() : EventListener {
    override fun sendEvent() {
        println("Adobe Analytics Api Call")
    }
}

class GFKAnalytics() : EventListener {
    override fun sendEvent() {
        println("GFK Analytics Api Call")
    }
}

class AppsflyerAnalytics() : EventListener {
    override fun sendEvent() {
        println("Appsflyer Analytics Api Call")
    }
}

class MixpanelAnalytics() : EventListener {
    override fun sendEvent() {
        println("Mixpanel Analytics Api Call")
    }
}

class AnalyticsFactory {
    enum class AnalyticsType {
        ADOBE, GFK, APPSFLYER, MIXPANEL
    }

    fun createAnalytics(analyticsType: AnalyticsType): EventListener {
        return when (analyticsType) {
            AnalyticsType.ADOBE -> AdobeAnalytics()
            AnalyticsType.GFK -> GFKAnalytics()
            AnalyticsType.APPSFLYER -> AppsflyerAnalytics()
            AnalyticsType.MIXPANEL -> MixpanelAnalytics()
        }
    }
}

fun main(){
    AnalyticsFactory().createAnalytics(AnalyticsFactory.AnalyticsType.APPSFLYER).sendEvent()
}