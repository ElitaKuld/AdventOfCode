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

fun createListOfRectangularPrisms(listOfData: List<String>): List<RectangularPrism> {
    val listOfRectangularPrisms = mutableListOf<RectangularPrism>()
    listOfData.forEach {
        listOfRectangularPrisms.add(
            RectangularPrism(
                it.split("x")[0].toInt(), it.split("x")[1].toInt(), it.split("x")[2].toInt()
            )
        )
    }
    return listOfRectangularPrisms
}

fun getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms: List<RectangularPrism>): Int {
    val listOfTotalPaperForEachGift = mutableListOf<Int>()
    for (i in listOfRectangularPrisms.indices) {
        listOfTotalPaperForEachGift.add(listOfRectangularPrisms[i].area + listOfRectangularPrisms[i].listOfSides.min())
    }
    return listOfTotalPaperForEachGift.sum()
}

fun getTotalLengthOfBow(listOfRectangularPrisms: List<RectangularPrism>): Int {
    val listOfTotalBowLength = mutableListOf<Int>()
    for (i in listOfRectangularPrisms.indices) {
        listOfTotalBowLength.add(
            listOfRectangularPrisms[i].length * listOfRectangularPrisms[i].width
                    * listOfRectangularPrisms[i].height
        )
    }
    return listOfTotalBowLength.sum()
}

fun getRequiredRibbonLength(listOfRectangularPrisms: List<RectangularPrism>): Int {
    val listOfRequiredRibbonLength = mutableListOf<Int>()
    for (i in listOfRectangularPrisms.indices) { // add the length of each present's ribbon to a list
        listOfRequiredRibbonLength.add(2 * (listOfRectangularPrisms[i].listOfSideMeasurements[0] + listOfRectangularPrisms[i].listOfSideMeasurements[1]))
    }
    return listOfRequiredRibbonLength.sum()
}

// En "After-funktion" för Del A:
fun getTotalSquareFeetOfPaperNeededAfter(listOfRectangularPrisms: List<RectangularPrism>): Int {
    return listOfRectangularPrisms.sumOf {
        3 * it.listOfSideMeasurements.min() * it.listOfSideMeasurements[1] +
                2 * it.listOfSideMeasurements[1] * it.listOfSideMeasurements[2] + 2 * it.listOfSideMeasurements[2] * it.listOfSideMeasurements[0]
    }
}

// En "After-funktion" för Del B:
fun getRequiredBowAndRibbonLength(listOfRectangularPrisms: List<RectangularPrism>): Int {
    return listOfRectangularPrisms.sumOf {
        2 * (it.listOfSideMeasurements[0] + it.listOfSideMeasurements[1]) +
                it.listOfSideMeasurements[0] * it.listOfSideMeasurements[1] * it.listOfSideMeasurements[2]
    }
}

fun main() {
    val filePathname = "src/main/kotlin/December_2_2015/data.txt"
    val listOfData = readFileToList(filePathname) // read data into a list
    val listOfRectangularPrisms =
        createListOfRectangularPrisms(listOfData) // create a list of Rectangular Prisms using data from the file (för att jobba objektorienterat)

    // Del A:
    val totalSquareFeetOfPaper =
        getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) // get gift wrap needed for all the presents
    println("How many total square feet of wrapping paper should they order?: $totalSquareFeetOfPaper")

    // Del B lösning 1:
    val totalBowLength =
        getTotalLengthOfBow(listOfRectangularPrisms) // get the sum of the bow length for all the presents
    val totalRibbonLength = getRequiredRibbonLength(listOfRectangularPrisms)

    val finalTotal = totalBowLength + totalRibbonLength // get the answer by adding bow length to ribbon length
    println("How many total feet of ribbon should they order?: $finalTotal")

    //------------------------------Solution After-----------------------------------------------------
    // // En bättre lösning med hjälp av koden från följande repo: https://github.com/Ruud-Wiegers/advent-of-code/blob/master/y2015/src/main/kotlin/adventofcode/y2015/Day02.kt
    // Del A:
    println(
        "How many total square feet of wrapping paper should they order?: " + getTotalSquareFeetOfPaperNeededAfter(
            listOfRectangularPrisms
        )
    )
    // Del B:
    println("How many total feet of ribbon should they order?: " + getRequiredBowAndRibbonLength(listOfRectangularPrisms))
}