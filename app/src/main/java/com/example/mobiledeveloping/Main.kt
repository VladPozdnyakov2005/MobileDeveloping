package com.example.mobiledeveloping

fun main() {
    task_1()
    task_2()

    //3 задание
    val n = 10
    val result = task_3(n)
    println("$n-ое число Фибоначчи: $result")

    //4 задание
    println("Введите строку:")
    val stroka = readLine()?: ""
    val letter  = task_4(stroka)
    if (letter == 0.0) {
        println("В строке нет букв латинского алфавита.")
    } else {
        println("Процент вхождения гласных букв: $letter%")
    }
}

fun task_1()
{
    println("Введите 3 числа: ")

    var num1 = readln().toDouble()
    var num2 = readln().toDouble()
    var num3 = readln().toDouble()

    val Cp: Double = (num1 + num2 + num3) / 3
    val wtf: Double = (num1 + num3) * 2 - num2 * 3

    println("Среднее чисел = $Cp")
    println("Разность удвоенной суммы первого и третьего чисел и утроенного второго числа = $wtf")
}

//2.	Дано число. Если оно более 100 или менее -100, то занулить, иначе увеличить его на 1. Решить через if и when

fun task_2()
{
    println("Введите любое число(в разумных рамках)")
    var num = readln().toInt()

    if(num > 100 || num < -100)
    {
        num = 0
        println(num)
    }
    else
    {
        num = num + 1
        println(num)
    }
}

//20. Найдите n-ое число Фибоначчи

fun task_3(n: Int): Int {
    if (n <= 0) return 0
    if (n == 1) return 1

    var a = 0
    var b = 1
    var result = 0

    for (i in 2..n) {
        result = a + b
        a = b
        b = result
    }
    return result
}

// 7. Введите с клавиатуры строку произвольной длины и
// подсчитайте процент вхождения гласных букв латинского алфавита в строку (не различая регистры).

fun task_4(input: String): Double {
    val glasn = "aeiouy"
    var glasnCount = 0
    var totalCount = 0

    for (char in input.toLowerCase()) {
        if (char in glasn) {
            glasnCount++
        }
        if (char in 'a'..'z') {
            totalCount++
        }
    }
    return if (totalCount == 0) {
        0.0
    } else {
        (glasnCount.toDouble() / totalCount) * 100
    }
}