package December_3_2015

import java.io.File

fun markHouseAsVisited(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    listOfColumnAndRow: MutableList<Int>,
    i: Int
) {
    if (dataToWorkWith[i] == '^') {
        listOfColumnAndRow[1]--
        gridOfHouses[listOfColumnAndRow[1]][listOfColumnAndRow[0]].visited = true
    } else if (dataToWorkWith[i] == '>') {
        listOfColumnAndRow[0]++
        gridOfHouses[listOfColumnAndRow[1]][listOfColumnAndRow[0]].visited = true
    } else if (dataToWorkWith[i] == 'v') {
        listOfColumnAndRow[1]++
        gridOfHouses[listOfColumnAndRow[1]][listOfColumnAndRow[0]].visited = true
    } else if (dataToWorkWith[i] == '<') {
        listOfColumnAndRow[0]--
        gridOfHouses[listOfColumnAndRow[1]][listOfColumnAndRow[0]].visited = true
    } else println("Something's wrong")
}

fun getGridWithVisitedHouses(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    column: Int,
    row: Int
): Array<Array<House>> {
    val newGridOfHouses = gridOfHouses.copyOf()
    val listOfColumnAndRow = mutableListOf(column, row)
    for (i in dataToWorkWith.indices) {
        markHouseAsVisited(dataToWorkWith, newGridOfHouses, listOfColumnAndRow, i)
    }
    return newGridOfHouses
}

fun getGridWithVisitedBySantaHouses(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    column: Int,
    row: Int
): Array<Array<House>> {
    val newGridOfHouses = gridOfHouses.copyOf()
    val listOfColumnAndRow = mutableListOf(column, row)
    for (i in 1..dataToWorkWith.length step 2) {
        markHouseAsVisited(dataToWorkWith, newGridOfHouses, listOfColumnAndRow, i)
    }
    return newGridOfHouses
}

fun getGridWithVisitedByRobotHouses(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    column: Int,
    row: Int
): Array<Array<House>> {
    val newGridOfHouses = gridOfHouses.copyOf()
    val listOfColumnAndRow = mutableListOf(column, row)
    for (i in dataToWorkWith.indices step 2) {
        markHouseAsVisited(dataToWorkWith, newGridOfHouses, listOfColumnAndRow, i)
    }
    return newGridOfHouses
}

fun countNumberOfVisitedHouses(gridOfHouses: Array<Array<House>>): Int {
    var numberOfHousesVisited = 0
    for (i in gridOfHouses.indices) {
        for (j in 0 until gridOfHouses[i].size) {
            if (gridOfHouses[i][j].visited) {
                numberOfHousesVisited++
            }
        }
    }
    return numberOfHousesVisited
}

// En "After-klass":
sealed class Direction {
    companion object {
        const val UP = '^'
        const val DOWN = 'v'
        const val LEFT = '<'
        const val RIGHT = '>'
    }
}

// En "After-funktion" för Del A:
fun getNumberOfVisitedHouses(dataToWorkWith: String): Int {
    var x = 0
    var y = 0

    return dataToWorkWith.map {
        when (it) {
            Direction.UP -> y++
            Direction.DOWN -> y--
            Direction.LEFT -> x--
            Direction.RIGHT -> x++
        }
        println("$x $y") // för att tydligt se vad som händer

        x to y
    }.plus(0 to 0).distinct().size
}

// En "After-funktion" för Del B:
fun getNumberOfHousesVisitedBySantaAndRobot(dataToWorkWith: String): Int {
    var santaX = 0
    var santaY = 0
    var robotX = 0
    var robotY = 0
    var counter = 0

    return dataToWorkWith.map {
        val isCounterEven = counter % 2 == 0

        when (it) {
            Direction.UP -> if (isCounterEven) santaY++ else robotY++
            Direction.DOWN -> if (isCounterEven) santaY-- else robotY--
            Direction.LEFT -> if (isCounterEven) santaX-- else robotX--
            Direction.RIGHT -> if (isCounterEven) santaX++ else robotX++
        }

        counter++

        if (isCounterEven) santaX to santaY
        else robotX to robotY
    }.plus(0 to 0).distinct().size
}

fun main() {
    // Del A:
    val dataToWorkWith = File("src/main/kotlin/December_3_2015/data.txt").readText() // String
    val possibleMaxSizeOfGrid = dataToWorkWith.length

    val column = possibleMaxSizeOfGrid / 2 // startposition
    val row = possibleMaxSizeOfGrid / 2 // startposition

    val gridOfHouses = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHouses[column][row].visited = true // mark the house at the starting point as visited

    val newGridOfHouses = getGridWithVisitedHouses(dataToWorkWith, gridOfHouses, column, row)

    println("How many houses receive at least one present?: ${countNumberOfVisitedHouses(newGridOfHouses)}")

    // Del B:
    val gridOfHousesNew = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHousesNew[column][row].visited = true // starting point

    val gridOfHousesAfterSantaVisit = getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHousesNew, column, row)

    val gridOfHousesAfterSantaAndRobotVisit =
        getGridWithVisitedByRobotHouses(dataToWorkWith, gridOfHousesAfterSantaVisit, column, row)

    println("Houses visited by Santa and Robot-Santa: ${countNumberOfVisitedHouses(gridOfHousesAfterSantaAndRobotVisit)}")

    // 2838 too high
    // 2650 too high
    // 2639 is the right answer!!! I GOT YOU! YAY!!

    //------------------------------Solution After-----------------------------------------------------
    // // En annan lösning med hjälp av koden från följande repo: https://github.com/timakden/advent-of-code/blob/main/src/main/kotlin/ru/timakden/aoc/year2015/Day03.kt
    // Del A:
    println("How many houses receive at least one present?: ${getNumberOfVisitedHouses(dataToWorkWith)}")
    // Del B:
    println("Houses visited by Santa and Robot-Santa: ${getNumberOfHousesVisitedBySantaAndRobot(dataToWorkWith)}")
}