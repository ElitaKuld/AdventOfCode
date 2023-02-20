package December_2_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GiftWrapKtTest {

    val filePathname = "src/main/kotlin/December_2_2015/testdata.txt"
    val measurements = "4x23x21"
    val listOfMeasurements = listOf("4x23x21", "22x29x19", "11x4x11")
    val cuboid = RectangularPrism(4, 23, 21)
    val cuboid2 = RectangularPrism(22, 29, 19)
    val cuboid3 = RectangularPrism(11, 4, 11)
    val listOfRectangularPrisms = listOf(cuboid, cuboid2, cuboid3)
    val listOfTotalPaperForEachGift = listOf(1402, 3632, 462)
    val listOfTotalBowLength = listOf(4 * 23 * 21, 22 * 29 * 19, 11 * 4 * 11)
    val listOfSideMeasurements = listOf(2, 3, 4)
    val listOfSideMeasurements2 = listOf(1, 1, 10)
    val listOfSideMeasurements3 = listOf(1, 1, 1)
    val newListOfSideMeasurements = listOf(4, 21)

    @Test
    fun readFileToListTest() {
        Assertions.assertEquals(readFileToList(filePathname).size, 5)
        Assertions.assertTrue(readFileToList(filePathname)[0] == "4x23x21")
        Assertions.assertTrue(readFileToList(filePathname)[1] == "22x29x19")
        Assertions.assertTrue(readFileToList(filePathname)[2] == "11x4x11")
        Assertions.assertTrue(readFileToList(filePathname)[3] == "8x10x5")
        Assertions.assertTrue(readFileToList(filePathname)[4] == "24x18x16")
    }

    @Test
    fun getShortestSideValueTest() {
        Assertions.assertEquals(getShortestSideValue(cuboid), 84)
        Assertions.assertTrue(getShortestSideValue(cuboid) != 92)
        Assertions.assertFalse(getShortestSideValue(cuboid) == 483)
    }

    @Test
    fun turnMeasurementsToListOfParametersTest() {
        Assertions.assertEquals(turnMeasurementsToListOfParameters(measurements).size, 3)
        Assertions.assertTrue(turnMeasurementsToListOfParameters(measurements)[0] == "4")
        Assertions.assertTrue(turnMeasurementsToListOfParameters(measurements)[1] == "23")
        Assertions.assertTrue(turnMeasurementsToListOfParameters(measurements)[2] == "21")
    }

    @Test
    fun createListOfRectangularPrismsTest() {
        Assertions.assertEquals(createListOfRectangularPrisms(listOfMeasurements).size, 3)
        Assertions.assertTrue(createListOfRectangularPrisms(listOfMeasurements)[0].height == cuboid.height)
        Assertions.assertTrue(createListOfRectangularPrisms(listOfMeasurements)[1].width == cuboid2.width)
        Assertions.assertTrue(createListOfRectangularPrisms(listOfMeasurements)[2].length == cuboid3.length)
    }

    @Test
    fun getTotalSquareFeetOfPaperTest() {
        Assertions.assertEquals(getTotalSquareFeetOfPaperList(listOfRectangularPrisms).size, 3)
        Assertions.assertTrue(getTotalSquareFeetOfPaperList(listOfRectangularPrisms)[0] == listOfTotalPaperForEachGift[0])
        Assertions.assertTrue(getTotalSquareFeetOfPaperList(listOfRectangularPrisms)[1] == 3632)
        Assertions.assertTrue(getTotalSquareFeetOfPaperList(listOfRectangularPrisms)[2] != 483)
    }

    @Test
    fun getTotalLengthOfBowListTest() {
        Assertions.assertEquals(getTotalLengthOfBowList(listOfRectangularPrisms).size, 3)
        Assertions.assertTrue(getTotalLengthOfBowList(listOfRectangularPrisms)[0] == listOfTotalBowLength[0])
        Assertions.assertTrue(getTotalLengthOfBowList(listOfRectangularPrisms)[1] == 12122)
        Assertions.assertTrue(getTotalLengthOfBowList(listOfRectangularPrisms)[2] != 461)
    }

    @Test
    fun highestSideMeasurementOutTest() {
        Assertions.assertEquals(highestSideMeasurementOut(listOfSideMeasurements).size, 2)
        Assertions.assertTrue(highestSideMeasurementOut(listOfSideMeasurements).contains(2))
        Assertions.assertTrue(highestSideMeasurementOut(listOfSideMeasurements).contains(3))
        Assertions.assertFalse(highestSideMeasurementOut(listOfSideMeasurements).contains(4))
        Assertions.assertEquals(highestSideMeasurementOut(listOfSideMeasurements2).size, 2)
        Assertions.assertTrue(highestSideMeasurementOut(listOfSideMeasurements2).contains(1))
        Assertions.assertTrue(highestSideMeasurementOut(listOfSideMeasurements2).contains(10))
        Assertions.assertEquals(highestSideMeasurementOut(listOfSideMeasurements3).size, 2)
        Assertions.assertTrue(highestSideMeasurementOut(listOfSideMeasurements3).contains(1))
        Assertions.assertTrue(
            highestSideMeasurementOut(listOfSideMeasurements3)[0] == highestSideMeasurementOut(
                listOfSideMeasurements3
            )[1]
        )
    }

    @Test
    fun getRequiredRibbonLengthTest() {
        Assertions.assertTrue(getRequiredRibbonLength(newListOfSideMeasurements) == 50)
        Assertions.assertFalse(getRequiredRibbonLength(newListOfSideMeasurements) == 25)
    }
}