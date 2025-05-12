package com.amitesh.designpattern.adapterPattern

//An Adapter is something like a connector that is used to connect two or more incompatible interface.
// This pattern lets the classes work together

interface FlightDetail{
    fun getPriceInInr(): Int
    fun getTimeInIst(): Double
    fun getFlightName(): String
}



class AirIndia: FlightDetail{

    override fun getPriceInInr(): Int {
        return  7000
    }

    override fun getTimeInIst(): Double {
        return 10.0
    }

    override fun getFlightName(): String {
        return "Air India"
    }

}

class EmiratesUS{
     fun getPriceInDollar(): Int {
        return 80
    }

     fun getTimeInEst(): Double {
        return 12.0
    }

    fun getFlightName(): String{
        return "Emirates"
    }

}

class EmiratesUsAdapter(val emiratesUS: EmiratesUS): FlightDetail{
    override fun getPriceInInr(): Int {
       return emiratesUS.getPriceInDollar().dollarToInr()
    }

    override fun getTimeInIst(): Double {
        return emiratesUS.getTimeInEst().estToIst()
    }

    override fun getFlightName(): String {
        return emiratesUS.getFlightName()
    }

}

fun Int.dollarToInr(): Int{
    return this* 80
}

fun Double.estToIst(): Double{
    return this* 1.08
}

fun showFlightDetails(flightDetail: FlightDetail){
    println("Flight name ${flightDetail.getFlightName()} :: Time inIst: ${flightDetail.getTimeInIst()}")
    println("Flight name ${flightDetail.getFlightName()} :: Price in Inr: ${flightDetail.getPriceInInr()}")
}

fun main(){
    var emirates = EmiratesUsAdapter(EmiratesUS())
    var airIndia = AirIndia()
    showFlightDetails(emirates)
    showFlightDetails(airIndia)

}

