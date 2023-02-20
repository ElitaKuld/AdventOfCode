package December_3_2015

import java.io.File

fun main() {
    // Del A:
    val dataToWorkWith = File("src/main/kotlin/December_3_2015/data.txt").readText() // String
    val possibleMaxSizeOfGrid = dataToWorkWith.length
    println("Antal instruktioner: $possibleMaxSizeOfGrid")

    var column = possibleMaxSizeOfGrid / 2
    var row = possibleMaxSizeOfGrid / 2

    val gridOfHouses = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHouses[column][row].visited = true // starting point

    for (i in dataToWorkWith.indices) {
        if (dataToWorkWith[i] == '^') {
            row--
            gridOfHouses[row][column].visited = true
        } else if (dataToWorkWith[i] == '>') {
            column++
            gridOfHouses[row][column].visited = true
        } else if (dataToWorkWith[i] == 'v') {
            row++
            gridOfHouses[row][column].visited = true
        } else if (dataToWorkWith[i] == '<') {
            column--
            gridOfHouses[row][column].visited = true
        } else println("Something's wrong")
    }

    var numberOfHousesVisited = 0

    for (i in gridOfHouses.indices) {
        for (j in 0 until gridOfHouses[i].size) {
            if (gridOfHouses[i][j].visited) {
                numberOfHousesVisited++
            }
        }
    }

    println("Houses visited: $numberOfHousesVisited")

    // Del B:
    var column2 = possibleMaxSizeOfGrid / 2
    var row2 = possibleMaxSizeOfGrid / 2

    val gridOfHouses2 = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHouses2[column2][row2].visited = true // starting point

    for (i in 1 .. dataToWorkWith.length step 2) {
        if (dataToWorkWith[i] == '^') {
            row2--
            gridOfHouses2[row2][column2].visited = true
        } else if (dataToWorkWith[i] == '>') {
            column2++
            gridOfHouses2[row2][column2].visited = true
        } else if (dataToWorkWith[i] == 'v') {
            row2++
            gridOfHouses2[row2][column2].visited = true
        } else if (dataToWorkWith[i] == '<') {
            column2--
            gridOfHouses2[row2][column2].visited = true
        } else println("Something's wrong")
    }

    var numberOfHousesVisited2 = 0

    for (i in gridOfHouses2.indices) {
        for (j in 0 until gridOfHouses2[i].size) {
            if (gridOfHouses2[i][j].visited) {
                numberOfHousesVisited2++
            }
        }
    }

    println("Houses visited by Santa: $numberOfHousesVisited2")

    var column3 = possibleMaxSizeOfGrid / 2
    var row3 = possibleMaxSizeOfGrid / 2

    val gridOfHouses3 = Array(possibleMaxSizeOfGrid) { Array(possibleMaxSizeOfGrid) { House() } }
    gridOfHouses3[column3][row3].visited = true // starting point

    for (i in 2 .. dataToWorkWith.length-1 step 2) {
        if (dataToWorkWith[i] == '^') {
            row3--
            gridOfHouses3[row3][column3].visited = true
        } else if (dataToWorkWith[i] == '>') {
            column3++
            gridOfHouses3[row3][column3].visited = true
        } else if (dataToWorkWith[i] == 'v') {
            row3++
            gridOfHouses3[row3][column3].visited = true
        } else if (dataToWorkWith[i] == '<') {
            column3--
            gridOfHouses3[row3][column3].visited = true
        } else println("Something's wrong")
    }

    var numberOfHousesVisited3 = 0

    for (i in gridOfHouses2.indices) {
        for (j in 0 until gridOfHouses3[i].size) {
            if (gridOfHouses3[i][j].visited) {
                numberOfHousesVisited3++
            }
        }
    }

    println("Houses visited by Robo: $numberOfHousesVisited3")

    val totalNumberOfHousesVisited = numberOfHousesVisited2+numberOfHousesVisited3
    println(totalNumberOfHousesVisited)
    // 2838 too high
}