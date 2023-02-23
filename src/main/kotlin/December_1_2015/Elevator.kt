package December_1_2015

import java.io.File

fun countLeftParenthesis(text: String): Int = text.count { it == '(' }

fun countRightParenthesis(text: String): Int = text.count { it == ')' }

fun getFloorNumberByComparing(parenthesisLeft: Int, parenthesisRight: Int) = parenthesisLeft - parenthesisRight

fun getFirstPositionOfBasement(text: String): Int {
    var leftP = 0
    var rightP = 0
    val position = 0
    for (i in 0..text.length) {
        if (text[i] == '(') {
            leftP++
            if (rightP > leftP) return i + 1
        }
        if (text[i] == ')') {
            rightP++
            if (rightP > leftP) return i + 1
        }
    }
    return position
}

// en "After"-funktion
fun getFirstPositionOfBasementAfter(text: String): Int {
    var position = 1
    var currentFloor = 0
    for (character in text) {
        when (character){
            '(' -> currentFloor++
            else ->  currentFloor--
        }
        if (currentFloor < 0) {
            break;
        }
        position++
    }
    return position
}

fun main() {

    val dataToWorkWith = File("src/main/kotlin/December_1_2015/data.txt").readText() // String
    println("Antal instruktioner: " + dataToWorkWith.length)

    // Del A:
    val parenthesisLeft = countLeftParenthesis(dataToWorkWith)
    val parenthesisRight = countRightParenthesis(dataToWorkWith)
    val floorNumber = getFloorNumberByComparing(parenthesisLeft, parenthesisRight)
    println("Instructions took Santa to the floor number: $floorNumber")

    // Del B:
    val position = getFirstPositionOfBasement(dataToWorkWith)
    println("The position of the character that causes Santa to first enter the basement: $position")

    //------------------------------Solution After-----------------------------------------------------
    // Del A:
    val floorNumberAfter = dataToWorkWith.count{it == '('} - dataToWorkWith.count{it == ')'}
    println("Instructions took Santa to the floor number: $floorNumberAfter")

    // Kan även skrivas på en och samma rad:
    println("Instructions took Santa to the floor number: " + (dataToWorkWith.count{it == '('} - dataToWorkWith.count{it == ')'}))

    // Del B:
    // En annorlunda lösning med hjälp av koden från följande repo: https://github.com/jvgrootveld/advent-of-code/blob/main/2015/src/main/kotlin/day01/Day01.kt
    val positionAfter = getFirstPositionOfBasementAfter(dataToWorkWith)
    println("The position of the character that causes Santa to first enter the basement: $positionAfter")
}