package December_2_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GiftWrapKtTest {

    val filePathname = "src/main/kotlin/December_2_2015/testdata.txt"
    val listOfMeasurements = listOf("4x23x21", "22x29x19", "11x4x11")
    val cuboid = RectangularPrism(4, 23, 21)
    val cuboid2 = RectangularPrism(22, 29, 19)
    val cuboid3 = RectangularPrism(11, 4, 11)
    val listOfRectangularPrisms = listOf(cuboid, cuboid2, cuboid3)
    val listOfTotalBowLength = listOf(4 * 23 * 21, 22 * 29 * 19, 11 * 4 * 11)
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
    fun createListOfRectangularPrismsTest() {
        Assertions.assertEquals(createListOfRectangularPrisms(listOfMeasurements).size, 3)
        Assertions.assertTrue(createListOfRectangularPrisms(listOfMeasurements)[0].height == cuboid.height)
        Assertions.assertTrue(createListOfRectangularPrisms(listOfMeasurements)[1].width == cuboid2.width)
        Assertions.assertTrue(createListOfRectangularPrisms(listOfMeasurements)[2].length == cuboid3.length)
    }

    @Test
    fun getTotalSquareFeetOfPaperTest() {
        Assertions.assertTrue(getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) == 5496)
        Assertions.assertTrue(getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) != 0)
    }

    @Test
    fun getTotalLengthOfBowListTest() {
        Assertions.assertTrue(getTotalLengthOfBow(listOfRectangularPrisms) == 14538)
        Assertions.assertTrue(getTotalLengthOfBow(listOfRectangularPrisms) != 0)
    }

    @Test
    fun getRequiredRibbonLengthTest() {
        Assertions.assertTrue(getRequiredRibbonLength(listOfRectangularPrisms) == 162)
        Assertions.assertFalse(getRequiredRibbonLength(listOfRectangularPrisms) == 0)
    }
}