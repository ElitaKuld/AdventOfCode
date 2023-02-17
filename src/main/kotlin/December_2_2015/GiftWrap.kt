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
    for (i in 0 until listOfRectangularPrisms.size) {
        listOfTotalPaperForEachGift.add(listOfRectangularPrisms[i].area + getShortestSideValue(listOfRectangularPrisms[i]))
    }
    return listOfTotalPaperForEachGift
}

fun getTotalLengthOfBowList(listOfRectangularPrisms: List<RectangularPrism>): List<Int> {
    val listOfTotalBowLength = mutableListOf<Int>()
    for (i in 0 until listOfRectangularPrisms.size) {
        listOfTotalBowLength.add(listOfRectangularPrisms[i].length * listOfRectangularPrisms[i].width
                * listOfRectangularPrisms[i].height)
    }
    return listOfTotalBowLength
}

fun main() {
    val filePathname = "src/main/kotlin/December_2_2015/data.txt"
    val listOfData = readFileToList(filePathname)
    println(listOfData.size)
    println(listOfData)

    // Del A:
    val listOfRectangularPrisms = createListOfRectangularPrisms(listOfData)
    val listOfTotalPaperForEachGift = getTotalSquareFeetOfPaperList(listOfRectangularPrisms)
    println(listOfTotalPaperForEachGift)
    val totalSquareFeetOfPaper = listOfTotalPaperForEachGift.sum()
    println(totalSquareFeetOfPaper)

    // Del B:
    val listOfTotalBowLength = getTotalLengthOfBowList(listOfRectangularPrisms)
    println(listOfTotalBowLength)
    val totalBowLength = listOfTotalBowLength.sum()
    println(totalBowLength)

}