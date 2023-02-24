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

// En "After-funktion" där jag använder mig av split istället för substring:
fun turnListOfDataIntoListOfInstructionsAfter(listOfData: List<String>): List<Instruction> {
    val listOfInstructions = mutableListOf<Instruction>()
    val regex = "\\s(?!on|off)".toRegex()
    for (data in listOfData) {
        listOfInstructions.add(
            Instruction(
                action = data.split(regex)[0],
                columnValueA = data.split(regex)[1].split(',')[0].toInt(),
                rowValueA = data.split(regex)[1].split(',')[1].toInt(),
                columnValueB = data.split(regex)[3].split(',')[0].toInt(),
                rowValueB = data.split(regex)[3].split(',')[1].toInt(),
            )
        )
    }
    return listOfInstructions
}


// En "After-klass":
sealed class ActionToDo {
    companion object {
        const val TURN_ON = "turn on"
        const val TURN_OFF = "turn off"
        const val TOGGLE = "toggle"
    }
}

// En "After-funktion" (använder mig av "when" istället för 3 if-satser + Konstanter)
fun setUpLightingAfter(instruction: Instruction, gridOfLights: Array<Array<Light>>) {
    for (i in instruction.columnValueA..instruction.columnValueB) {
        for (j in instruction.rowValueA..instruction.rowValueB) {
            when (instruction.action) {
                ActionToDo.TURN_ON -> gridOfLights[i][j].lightIsLit = true
                ActionToDo.TURN_OFF -> gridOfLights[i][j].lightIsLit = false
                ActionToDo.TOGGLE -> gridOfLights[i][j].lightIsLit = !gridOfLights[i][j].lightIsLit
            }
        }
    }
}

// En "After-funktion" (använder mig av "when" istället för 3 if-satser + Konstanter)
fun changeLevelOfBrightnessAfter(instruction: Instruction, gridOfLights: Array<Array<Light>>) {
    for (i in instruction.columnValueA..instruction.columnValueB) {
        for (j in instruction.rowValueA..instruction.rowValueB) {
            when (instruction.action) {
                ActionToDo.TURN_ON -> gridOfLights[i][j].brightnessLevel += 1
                ActionToDo.TURN_OFF -> if (gridOfLights[i][j].brightnessLevel > 0) gridOfLights[i][j].brightnessLevel -= 1
                ActionToDo.TOGGLE -> gridOfLights[i][j].brightnessLevel += 2
            }
        }
    }
}

fun getNumberOfLightsLit(gridOfLights: Array<Array<Light>>): Int {
    var numberOfLightsLit = 0
    for (i in gridOfLights.indices) {
        for (j in 0 until gridOfLights[i].size) {
            if (gridOfLights[i][j].lightIsLit) {
                numberOfLightsLit++
            }
        }
    }
    return numberOfLightsLit
}

fun getTotalLevelOfBrightness(gridOfLights: Array<Array<Light>>): Int {
    var totalLevelOfBrightness = 0
    for (i in gridOfLights.indices) {
        for (j in 0 until gridOfLights[i].size) {
            totalLevelOfBrightness += gridOfLights[i][j].brightnessLevel
        }
    }
    return totalLevelOfBrightness
}

fun main() {
    // Del A:
    val filePathname = "src/main/kotlin/December_6_2015/data.txt"
    val listOfData = readFileToList(filePathname) // read data into a list

    val column = 1000
    val row = 1000

    val gridOfLights = Array(column) { Array(row) { Light() } }

    val listOfInstructions = turnListOfDataIntoListOfInstructions(listOfData)
    for (i in listOfInstructions) println(i) // Demonstration

    for (i in listOfInstructions.indices) {
        setUpLighting(listOfInstructions[i], gridOfLights)
    }

    println("How many lights are lit?: " + getNumberOfLightsLit(gridOfLights))

    // Del B:
    for (i in listOfInstructions.indices) {
        changeLevelOfBrightness(listOfInstructions[i], gridOfLights)
    }

    println(
        "What is the total brightness of all lights combined after following Santa's instructions?: " + getTotalLevelOfBrightness(
            gridOfLights
        )
    )

    //------------------------------Solution After-----------------------------------------------------
    // En förbättrad lösning inspirerad av koden från följande repo:
    // https://github.com/timakden/advent-of-code/blob/main/src/main/kotlin/ru/timakden/aoc/year2015/Day06.kt
    // Del A:
    val newListOfInstructions = turnListOfDataIntoListOfInstructionsAfter(listOfData)
    val newGridOfLights = Array(column) { Array(row) { Light() } }
    for (i in newListOfInstructions.indices) {
        setUpLightingAfter(newListOfInstructions[i], newGridOfLights)
    }
    println("How many lights are lit?: " + getNumberOfLightsLit(newGridOfLights))

    // Del B:
    for (i in newListOfInstructions.indices) {
        changeLevelOfBrightnessAfter(newListOfInstructions[i], newGridOfLights)
    }
    println(
        "What is the total brightness of all lights combined after following Santa's instructions?: " + getTotalLevelOfBrightness(
            newGridOfLights
        )
    )
}