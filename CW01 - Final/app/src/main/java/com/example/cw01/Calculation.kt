package com.example.cw01

import kotlin.random.Random

class Calculation {

    var finalValue = 0
    var termCount = 0
    var operatorList = mutableListOf<String>()
    var numbersList = mutableListOf<Int>()
    var finalExpression = ""

    private fun randomNumber(): Int {
        return Random.nextInt(1, 21)
    }

    private fun randomOperator(): String {
        val mathOperators = listOf("+", "-", "*", "/")
        return mathOperators.random()
    }

    private fun numberOfTerms(): Int {
        return Random.nextInt(0, 4)
    }

    private fun firstOperatorValue(): Int {
        var num1 = 0
        var num2 = 0
        var operator1 = ""
        do {
            num1 = randomNumber()
            operator1 = randomOperator()
            num2 = randomNumber()
        } while (num1%num2 != 0 || num1*num2 > 100)

        operatorList.add(operator1)
        numbersList.add(num1)
        numbersList.add(num2)

        when (operator1) {
            "+" -> {
                finalValue = num1 + num2
                return finalValue
            }
            "-" -> {
                finalValue = num1 - num2
                return finalValue
            }
            "*" -> {
                finalValue = num1 * num2
                return finalValue
            }
            "/" -> {
                finalValue = num1 / num2
                return finalValue
            }
            else -> return finalValue
        }
    }

    private fun secondCalculation(): Int {
        var nextOperator = ""
        var nextNum = 0
        do {
            nextOperator = randomOperator()
            nextNum = randomNumber()
        } while (finalValue%nextNum != 0 || finalValue*nextNum > 100)

        operatorList.add(nextOperator)
        numbersList.add(nextNum)

        when (nextOperator) {
            "+" -> {
                finalValue += nextNum
                return finalValue
            }
            "-" -> {
                finalValue -= nextNum
                return finalValue
            }
            "*" -> {
                finalValue *= nextNum
                return finalValue
            }
            "/" -> {
                finalValue /= nextNum
                return finalValue
            }
            else -> return finalValue
        }
    }

    fun finalOperatorValue(): Int {
        termCount = numberOfTerms()

        when (termCount) {
            0 -> {
                finalValue = randomNumber()
                numbersList.add(finalValue)
            }
            1 -> {
                firstOperatorValue()
            }
            2 -> {
                firstOperatorValue()
                secondCalculation()
            }
            3 -> {
                firstOperatorValue()
                secondCalculation()
                var nextOperator = ""
                var nextNum = 0
                do {
                    nextOperator = randomOperator()
                    nextNum = randomNumber()
                } while (finalValue%nextNum != 0 || finalValue*nextNum > 100)

                operatorList.add(nextOperator)
                numbersList.add(nextNum)

                when (nextOperator) {
                    "+" -> {
                        finalValue += nextNum
                    }
                    "-" -> {
                        finalValue -= nextNum
                    }
                    "*" -> {
                        finalValue *= nextNum
                    }
                    "/" -> {
                        finalValue /= nextNum
                    }
                }
            }
            else -> finalValue
        }
        println(finalValue)
        return finalValue
    }

    //the method that generates the
    fun stringExpression (): String {
        when (termCount) {
            0 -> {
                finalExpression = "${numbersList[0]}"
                println(finalExpression)
                return finalExpression
            }
            1 -> {
                finalExpression = "${numbersList[0].toString()}${operatorList[0]}${numbersList[1].toString()}"
                println(finalExpression)
                return finalExpression
            }
            2 -> {
                finalExpression = "(${numbersList[0].toString()}${operatorList[0]}${numbersList[1].toString()})" +
                        "${operatorList[1]}${numbersList[2].toString()}"
                println(finalExpression)
                return finalExpression
            }
            3 -> {
                finalExpression = "((${numbersList[0].toString()}${operatorList[0]}${numbersList[1].toString()})" +
                        "${operatorList[1]}${numbersList[2].toString()})${operatorList[2]}${numbersList[3].toString()}"
                println(finalExpression)
                return finalExpression
            }
            else -> {
                println(finalExpression)
                return finalExpression
            }
        }
    }
}