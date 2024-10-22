package com.example.mobiledeveloping

class LabWork2 {
    fun main() {

        //1 задание
        val number1 = 45
        val result1 = task1(number1)
        println("Результат: $result1")

        //2 задание
        val number2 = 30 // Пример значения number
        val result2 = task2(number2)
        println("Число $number2 делится на 10 без остатка: $result2")


        //3 Задание
        println("Введите размерность массива: ")
        val Size = readln().toInt()
        val array = IntArray(size = Size)
        for (i in 0 until array.size) {
            array[i] = (-100 until 501).random()
        }
        println("Исходный массив: ")
        task3(array)
        var arrayTask1 = array.map(::task1)
        print(arrayTask1)
        var arrayTask2 = array.filter(::task2)
        print(arrayTask2)
        var arrayTask12 = array.filter(::task2).map(::task1)
        println(arrayTask12)

        //4 Задание
        println("Задание 4")
        val Task4_Array = arrayOf(7, 34, 1, -30, -98, 56)
        val size = Task4_Array.size
        val Task4_newArray = Array(size) { index -> task1(Task4_Array[index]) }
        printArray<Int>(Task4_newArray, "newArray")

        //1 Задание
        //Номер 1
        //Тип данных для функции, принимающий две Int, одну String переменные, и ничего не возвращающей.
        //Ответ: (Int, Int, String) -> Unit

        //Номер 2
        //Тип данных для функции, не принимающей параметры, и возвращающей строку.
        //Ответ: () -> String

        //Номер 3
        //Тип данных для функции, принимающей другую функцию (она ничего не принимает и ничего не возвращает), и ничего не возвращающей.
        //Ответ: (() -> Unit) -> Unit
    }

    fun task1(number: Int): Int {
        return if (number in 10..99) {
            number
        } else {
            0
        }
    }


    fun task2(number: Int): Boolean {
        // Определение лямбда-выражения внутри метода
        val Check: (Int) -> Boolean = { it % 10 == 0 }
        return Check(number)
    }


    fun task3(numbers: IntArray) {
        for (i in 0..numbers.size - 1) {
            print("${numbers[i]} ")
        }

    }

    fun <T> printArray(array: Array<T>, arrayName: String) {
        println("$arrayName = " + array.contentToString())
    }


    fun Array<Int>.filterAndMap(
        filterFunction: (Int) -> Boolean,
        mapFunction: (Int) -> Int
    ): Array<Int> {
        return this.filter(filterFunction).map(mapFunction).toTypedArray()
    }
}

