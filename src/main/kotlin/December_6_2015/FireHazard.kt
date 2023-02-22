package December_6_2015

import java.io.File
import java.util.*

fun readFileToList(pathname: String): List<String> {
    val scanner = Scanner(File(pathname))
    val list = mutableListOf<String>()
    while (scanner.hasNextLine()) {
        list.add(scanner.nextLine())
    }
    return list
}

fun turnListOfDataIntoListOfInstructions(listOfData: List<String>): List<Instruction> {
    val listOfInstructions = mutableListOf<Instruction>()
    for (data in listOfData) {
        listOfInstructions.add(
            Instruction(
                action = data.substring(0, data.indexOf(data.first { char -> char.isDigit() })).trim(),
                columnValueA = data.substring(data.indexOf(data.first { char -> char.isDigit() }), data.indexOf(","))
                    .toInt(),
                rowValueA = data.substring(data.indexOf(",") + 1)
                    .substring(0, data.substring(data.indexOf(",") + 1).indexOf(" ")).toInt(),
                columnValueB = data.substring(data.lastIndexOf(" ") + 1, data.lastIndexOf(",")).toInt(),
                rowValueB = data.substring(data.lastIndexOf(",") + 1).toInt()
            )
        )
    }
    return listOfInstructions
}

fun setUpLighting(instruction: Instruction, gridOfLights: Array<Array<Light>>) {
    val columnToBegin = instruction.columnValueA
    val rowToBegin = instruction.rowValueA
    val columnToEnd = instruction.columnValueB
    val rowToEnd = instruction.rowValueB
    if (instruction.action == "turn on") {
        for (i in columnToBegin..columnToEnd) {
            for (j in rowToBegin..rowToEnd) {
                gridOfLights[i][j].lightIsLit = true
            }
        }
    }
    if (instruction.action == "turn off") {
        for (i in columnToBegin..columnToEnd) {
            for (j in rowToBegin..rowToEnd) {
                gridOfLights[i][j].lightIsLit = false
            }
        }
    }
    if (instruction.action == "toggle") {
        for (i in columnToBegin..columnToEnd) {
            for (j in rowToBegin..rowToEnd) {
                gridOfLights[i][j].lightIsLit = !gridOfLights[i][j].lightIsLit
            }
        }
    }
}

fun changeLevelOfBrightness(instruction: Instruction, gridOfLights: Array<Array<Light>>) {
    val columnToBegin = instruction.columnValueA
    val rowToBegin = instruction.rowValueA
    val columnToEnd = instruction.columnValueB
    val rowToEnd = instruction.rowValueB
    if (instruction.action == "turn on") {
        for (i in columnToBegin..columnToEnd) {
            for (j in rowToBegin..rowToEnd) {
                gridOfLights[i][j].brightnessLevel += 1
            }
        }
    }
    if (instruction.action == "turn off") {
        for (i in columnToBegin..columnToEnd) {
            for (j in rowToBegin..rowToEnd) {
                if (gridOfLights[i][j].brightnessLevel > 0)
                    gridOfLights[i][j].brightnessLevel -= 1
            }
        }
    }
    if (instruction.action == "toggle") {
        for (i in columnToBegin..columnToEnd) {
            for (j in rowToBegin..rowToEnd) {
                gridOfLights[i][j].brightnessLevel += 2
            }
        }
    }
}

fun main() {
    // Del A:
    val filePathname = "src/main/kotlin/December_6_2015/data.txt"
    val listOfData = readFileToList(filePathname) // read data into a list

    val column = 1000
    val row = 1000

    val gridOfLights = Array(column) { Array(row) { Light() } }

    // Demonstration
    val listOfInstructions = turnListOfDataIntoListOfInstructions(listOfData)
    for (i in listOfInstructions) println(i)

    for (i in listOfInstructions.indices) {
        setUpLighting(listOfInstructions[i], gridOfLights)
    }

    var numberOfLightsLit = 0
    for (i in gridOfLights.indices) {
        for (j in 0 until gridOfLights[i].size) {
            if (gridOfLights[i][j].lightIsLit) {
                numberOfLightsLit++
            }
        }
    }
    println("How many lights are lit?: $numberOfLightsLit")

    // Correct!!! Yay!


    // Del B:
    for (i in listOfInstructions.indices) {
        changeLevelOfBrightness(listOfInstructions[i], gridOfLights)
    }

    var totalLevelOfBrightness = 0
    for (i in gridOfLights.indices) {
        for (j in 0 until gridOfLights[i].size) {
            totalLevelOfBrightness += gridOfLights[i][j].brightnessLevel
        }
    }
    println("What is the total brightness of all lights combined after following Santa's instructions?: $totalLevelOfBrightness")

    // That's the right answer! You are one gold star closer to powering the weather machine.
}