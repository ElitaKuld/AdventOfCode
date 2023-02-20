package December_2_2015

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

fun createListOfRectangularPrisms(listOfMeasurements: List<String>): List<RectangularPrism> {
    val listOfRectangularPrisms = mutableListOf<RectangularPrism>()
    listOfMeasurements.forEach {
        listOfRectangularPrisms.add(
            RectangularPrism(
                turnMeasurementsToListOfParameters(it)[0].toInt(),
                turnMeasurementsToListOfParameters(it)[1].toInt(), turnMeasurementsToListOfParameters(it)[2].toInt()
            )
        )
    }
    return listOfRectangularPrisms
}

fun turnMeasurementsToListOfParameters(measurements: String): List<String> {
    val delimiter = "x"
    return measurements.split(delimiter)
}

fun getShortestSideValue(cuboid: RectangularPrism): Int {
    val listOfSides = listOf(cuboid.sideA, cuboid.sideB, cuboid.sideC)
    return listOfSides.min()
}

fun getTotalSquareFeetOfPaperList(listOfRectangularPrisms: List<RectangularPrism>): List<Int> {
    val listOfTotalPaperForEachGift = mutableListOf<Int>()
    for (i in listOfRectangularPrisms.indices) {
        listOfTotalPaperForEachGift.add(listOfRectangularPrisms[i].area + getShortestSideValue(listOfRectangularPrisms[i]))
    }
    return listOfTotalPaperForEachGift
}

fun getTotalLengthOfBowList(listOfRectangularPrisms: List<RectangularPrism>): List<Int> {
    val listOfTotalBowLength = mutableListOf<Int>()
    for (i in listOfRectangularPrisms.indices) {
        listOfTotalBowLength.add(
            listOfRectangularPrisms[i].length * listOfRectangularPrisms[i].width
                    * listOfRectangularPrisms[i].height
        )
    }
    return listOfTotalBowLength
}

// funktion för att hitta 2 kortaste sidor (övertänkt)
fun highestSideMeasurementOut(listOfSideMeasurements: List<Int>): List<Int> {
    val newListOfSideMeasurements = mutableListOf<Int>()
    val list = listOfSideMeasurements.distinct()
    for (i in list.indices) {
        if (list.size == 3 && list[i] != list.max())
            newListOfSideMeasurements.add(list[i])
        else if (list.size == 2)
            newListOfSideMeasurements.add(list[i])
        else if (list.size == 1)
            repeat(2) { newListOfSideMeasurements.add(list[i]) }
    }
    return newListOfSideMeasurements
}

fun getRequiredRibbonLength(newListOfSideMeasurements: List<Int>): Int {
    return newListOfSideMeasurements.sum() * 2
}

fun main() {
    val filePathname = "src/main/kotlin/December_2_2015/data.txt"
    val listOfData = readFileToList(filePathname) // read data into a lis
    println(listOfData.size)
    println(listOfData)

    // Del A:
    val listOfRectangularPrisms =
        createListOfRectangularPrisms(listOfData) // create a list of Rectangular Prisms using data from the file
    val listOfTotalPaperForEachGift =
        getTotalSquareFeetOfPaperList(listOfRectangularPrisms) // get gift wrap needed for each present
    println(listOfTotalPaperForEachGift)
    val totalSquareFeetOfPaper = listOfTotalPaperForEachGift.sum() // get the sum of gift wrap for all the presents
    println("Answer A: $totalSquareFeetOfPaper")

    // Del B lösning 1:
    val listOfTotalBowLength = getTotalLengthOfBowList(listOfRectangularPrisms) // get bow length for each present
    println(listOfTotalBowLength)
    val totalBowLength = listOfTotalBowLength.sum() // get the sum of the bow length for all the presents
    println(totalBowLength)

    val listOfSideMeasurementsOfEveryPrism =
        listOfRectangularPrisms.map(RectangularPrism::listOfSideMeasurements)
            .toList() // create a list with all three sides of each present
    val listOfRequiredRibbonLength = mutableListOf<Int>()
    for (i in listOfSideMeasurementsOfEveryPrism.indices) { // add the length of each present's ribbon to a list
        listOfRequiredRibbonLength.add(2 * (listOfSideMeasurementsOfEveryPrism[i][0] + listOfSideMeasurementsOfEveryPrism[i][1]))
    }
    println(listOfRequiredRibbonLength)
    val totalRibbonLength = listOfRequiredRibbonLength.sum() // get the total ribbon length for all the presents
    println(totalRibbonLength)
    val finalTotal = totalBowLength + totalRibbonLength // get the answer by adding bow length to ribbon length
    println("Answer B: $finalTotal")

    // Övertänkt/missförstått lösning:
    /*
    listOfSideMeasurementsOfEveryPrism.forEach { println(highestSideMeasurementOut(it)) }
    for (i in listOfSideMeasurementsOfEveryPrism.indices) { // add the length of each present's ribbon to a list
        listOfRequiredRibbonLength.add(
            getRequiredRibbonLength(
                highestSideMeasurementOut(
                    listOfSideMeasurementsOfEveryPrism[i]
                )
            )
        )
    }
    println(listOfRequiredRibbonLength)
    val totalRibbonLength = listOfRequiredRibbonLength.sum() // get the total length for all the presents
    println(totalRibbonLength)
    val finalTotal = totalBowLength + totalRibbonLength
    println("Answer B: $finalTotal")*/

}