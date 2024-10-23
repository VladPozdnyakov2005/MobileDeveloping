package com.example.mobiledeveloping
import java.util.Locale
import kotlin.math.sqrt


fun main(){
    task1() //Задание 1
    println(getFullName("Vladislav", "Pozdnyakov")) //Задание 2
    println(calculateSquareRoot(2.44 )) //Задание 3
    println(getStringLength('a')) //Задание 4
}

fun task1(){
    var number: Int? = null
    println(number) //Вывод: "null"

    number = 20
    val text: String = number.toString()
    println(text) //Вывод: "20"
}

fun getFullName(firstName: String?, lastName: String?): String{
    val getPartOfName: (String?) -> String = { part: String? -> part?.toUpperCaseFirstChar() ?: "Unknown" }

    return "${getPartOfName(firstName)} ${getPartOfName(lastName)}"
}

fun String.toUpperCaseFirstChar(): String{
    return  this.replaceFirstChar { if (it. isLowerCase()) it. titlecase(Locale.getDefault()) else it. toString() }
}

fun calculateSquareRoot(number: Double?): Double {
    return sqrt(number!!)
}

fun getStringLength(any: Any?): Int{
    val string = any as? String ?: return -1
    return string.length
}