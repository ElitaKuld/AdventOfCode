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
}