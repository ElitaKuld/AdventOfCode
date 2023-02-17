package December_1_2015

import java.io.File

fun countLeftParenthesis(text: List<Char>): Int = text.filter { it != ')' }.size

fun countRightParenthesis(text: List<Char>): Int = text.filter { it != '(' }.size

fun getFloorNumberByComparing(parenthesisLeft: Int, parenthesisRight: Int) = parenthesisLeft - parenthesisRight

fun getFirstPositionOfBasement(text: List<Char>): Int {
    var leftP = 0
    var rightP = 0
    val position = 0
    for (i in 0..text.size) {
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
    val listOfData = dataToWorkWith.toList()
    println(listOfData.size)

    // Del A:
    val parenthesisLeft = countLeftParenthesis(listOfData)
    val parenthesisRight = countRightParenthesis(listOfData)
    val floorNumber = getFloorNumberByComparing(parenthesisLeft, parenthesisRight)
    println(floorNumber)

    // Del B:
    val position = getFirstPositionOfBasement(listOfData)
    println(position)
}