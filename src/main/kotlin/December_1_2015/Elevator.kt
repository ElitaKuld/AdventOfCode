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
}