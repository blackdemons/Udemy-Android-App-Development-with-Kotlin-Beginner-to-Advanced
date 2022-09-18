package com.vid.firstkotlinprogram

fun main(args: Array<String>) {

    print("Please enter 3 number : ")
    var number1: Int = readLine()!!.toInt()
    var number2: Int = readLine()!!.toInt()
    var number3: Int = readLine()!!.toInt()

    var largeNumber: Int

    if (number1 >= number2) {
        if (number1 >= number3) { largeNumber = number1 }
        else {largeNumber = number3 }
    }else{
        if (number2 >= number3) { largeNumber = number2}
        else { largeNumber = number3 }
    }

    println("The largest number  is $largeNumber")

    /*
    print("Enter your age")
    var yourAge: Int = readLine()!!.toInt()

    if (yourAge < 13) { print("Your are a child.") }
    else if (yourAge < 19 ) { print("Your are a teenager.") }
    else {
        if (yourAge < 65) { print("Your are a adult.") }
        else { print("Your are a senior. ")}
    }

     */
}