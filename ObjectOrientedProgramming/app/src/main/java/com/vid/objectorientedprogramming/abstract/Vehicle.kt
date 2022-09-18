package com.vid.objectorientedprogramming.abstract

abstract class Vehicle {

    // Abstract function
    abstract fun vehicleName(name: String) : String

    // Non-abstarct function
    fun vehicleType(type: String) : String
    {
        return  type
    }

    // Abstract property
    abstract var model: Int

    // Non-abstract
    var speed: Int? = null
}