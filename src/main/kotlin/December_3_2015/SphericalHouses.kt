package December_3_2015

import java.io.File

fun markHousesAsVisited(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    column: Int,
    row: Int
): Array<Array<House>> {
    val newGridOfHouses = gridOfHouses.copyOf()
    var thisColumn = column
    var thisRow = row
    for (i in dataToWorkWith.indices) {
        if (dataToWorkWith[i] == '^') {
            thisRow--
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == '>') {
            thisColumn++
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == 'v') {
            thisRow++
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == '<') {
            thisColumn--
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else println("Something's wrong")
    }
    return newGridOfHouses

}

fun markHousesAsVisitedBySanta(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    column: Int,
    row: Int
): Array<Array<House>> {
    val newGridOfHouses = gridOfHouses.copyOf()
    var thisColumn = column
    var thisRow = row
    for (i in 1..dataToWorkWith.length step 2) {
        if (dataToWorkWith[i] == '^') {
            thisRow--
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == '>') {
            thisColumn++
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == 'v') {
            thisRow++
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == '<') {
            thisColumn--
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else println("Something's wrong")
    }
    return newGridOfHouses

}

fun markHousesAsVisitedByRobot(
    dataToWorkWith: String,
    gridOfHouses: Array<Array<House>>,
    column: Int,
    row: Int
): Array<Array<House>> {
    val newGridOfHouses = gridOfHouses.copyOf()
    var thisColumn = column
    var thisRow = row
    for (i in 0..dataToWorkWith.length-1 step 2) {
        if (dataToWorkWith[i] == '^') {
            thisRow--
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == '>') {
            thisColumn++
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == 'v') {
            thisRow++
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else if (dataToWorkWith[i] == '<') {
            thisColumn--
            newGridOfHouses[thisRow][thisColumn].visited = true
        } else println("Something's wrong")
    }
    return newGridOfHouses

}

fun main() {
    // Del A:
    val dataToWorkWith = File("src/main/kotlin/December_3_2015/data.txt").readText() // String
    val possibleMaxSizeOfGrid = dataToWorkWith.length
    println("Antal instruktioner: $possibleMaxSizeOfGrid")

    val column = possibleMaxSizeOfGrid / 2
    val row = possibleMaxSizeOfGrid / 2

    val gridOfHouses = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHouses[column][row].visited = true // starting point

    val newGridOfHouses = markHousesAsVisited(dataToWorkWith, gridOfHouses, column, row)

    var numberOfHousesVisited = 0

    for (i in newGridOfHouses.indices) {
        for (j in 0 until newGridOfHouses[i].size) {
            if (newGridOfHouses[i][j].visited) {
                numberOfHousesVisited++
            }
        }
    }


    println("Houses visited: $numberOfHousesVisited")

    // Del B:

    val gridOfHouses2 = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHouses2[column][row].visited = true // starting point

    val newGridOfHouses2 = markHousesAsVisitedBySanta(dataToWorkWith, gridOfHouses2, column, row)

    var numberOfHousesVisitedBySanta = 0

    for (i in newGridOfHouses2.indices) {
        for (j in 0 until newGridOfHouses2[i].size) {
            if (newGridOfHouses2[i][j].visited) {
                numberOfHousesVisitedBySanta++
            }
        }
    }

    println("Houses visited by Santa: $numberOfHousesVisitedBySanta")


    val newGridOfHouses3 = markHousesAsVisitedByRobot(dataToWorkWith, newGridOfHouses2, column, row)

    var numberOfHousesVisitedByRobotANDSanta = 0

    for (i in newGridOfHouses3.indices) {
        for (j in 0 until newGridOfHouses3[i].size) {
            if (newGridOfHouses3[i][j].visited) {
                numberOfHousesVisitedByRobotANDSanta++
            }
        }
    }

    println("Houses visited by Santa and Robo-Santa: $numberOfHousesVisitedByRobotANDSanta")

    // 2838 too high
    // 2650 too high
    // 2639 is the right answer!!! I GOT YOU! YAY!!

}