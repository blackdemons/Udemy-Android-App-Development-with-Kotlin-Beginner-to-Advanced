package com.vid.objectorientedprogramming

fun main(args: Array<String>) {

    // Creating an object from class
    /*
    var myCars = Cars()
    myCars.name = "Ferrari"
    myCars.model = 2021

    var myCars2 = Cars()
    myCars2.name = "Mercedes"
    myCars2.model = 2010

    println("My first car's name is ${myCars.name} and its model is ${myCars.model}")
    println("My second car's name is ${myCars2.name} and its model is ${myCars2.model}")

     */

    //var myNewCar = MyCars("Ferrari", 2021)
    //println("My car's name is ${myNewCar.name} and its model is ${myNewCar.model}")

    var mySecondsCar = MySecondsCars("Mercedes", 2000)

    mySecondsCar.name = "Opel"
    //mySecondsCar.model = 2010 // set

    println("My second car's name is ${mySecondsCar.name} and its model is ${mySecondsCar.model}") // get

}