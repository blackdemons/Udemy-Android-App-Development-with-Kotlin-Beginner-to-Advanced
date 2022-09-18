package com.vid.firstkotlinprogram

fun main(args: Array<String>) {

    print("Please enter the first number")
    var number1: Int = readLine()!!.toInt()

    print("Please enter the second number")
    var number2: Int = readLine()!!.toInt()

    show(number1, number2)
    var a = add(number1, number2)

    println("Sum of the two numbers  = $a")

    var b = findMinimumNumber(number1, number2)
    println("Minimum number = $b")
}

fun show(num1: Int, num2: Int) {

    println("You enter $num1 and $num2")
}

fun add(num1: Int, num2: Int): Int {

    var sum: Int = 0
    sum = num1 + num2
    return sum
}

fun findMinimumNumber(num1: Int, num2: Int): Int {
    var minimumNumber: Int = 0
    if (num1 > num2) {
        minimumNumber = num1
    } else {
        minimumNumber = num2
    }
    return minimumNumber
}