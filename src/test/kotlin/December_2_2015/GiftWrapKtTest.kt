package December_2_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GiftWrapKtTest {

    val filePathname = "src/main/kotlin/December_2_2015/testdata.txt"
    val listOfMeasurements = listOf("4x23x21", "22x29x19", "11x4x11")
    val cuboid = RectangularPrism(4, 23, 21)
    val cuboid2 = RectangularPrism(22, 29, 19)
    val cuboid3 = RectangularPrism(11, 4, 11)
    val listOfRectangularPrisms = listOf(cuboid, cuboid2, cuboid3)


    @Test
    fun readFileToListTest() {
        Assertions.assertEquals(readFileToList(filePathname).size, 5)
        assertTrue(readFileToList(filePathname)[0] == "4x23x21")
        assertTrue(readFileToList(filePathname)[1] == "22x29x19")
        assertTrue(readFileToList(filePathname)[2] == "11x4x11")
        assertTrue(readFileToList(filePathname)[3] == "8x10x5")
        assertTrue(readFileToList(filePathname)[4] == "24x18x16")
    }

    @Test
    fun createListOfRectangularPrismsTest() {
        Assertions.assertEquals(createListOfRectangularPrisms(listOfMeasurements).size, 3)
        assertTrue(createListOfRectangularPrisms(listOfMeasurements)[0].height == cuboid.height)
        assertTrue(createListOfRectangularPrisms(listOfMeasurements)[1].width == cuboid2.width)
        assertTrue(createListOfRectangularPrisms(listOfMeasurements)[2].length == cuboid3.length)
    }

    @Test
    fun getTotalSquareFeetOfPaperTest() {
        assertTrue(getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) == 5496)
        assertTrue(getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) != 0)
    }

    @Test
    fun getTotalLengthOfBowListTest() {
        assertTrue(getTotalLengthOfBow(listOfRectangularPrisms) == 14538)
        assertTrue(getTotalLengthOfBow(listOfRectangularPrisms) != 0)
    }

    @Test
    fun getRequiredRibbonLengthTest() {
        assertTrue(getRequiredRibbonLength(listOfRectangularPrisms) == 162)
        assertFalse(getRequiredRibbonLength(listOfRectangularPrisms) == 0)
    }

    @Test
    fun getTotalSquareFeetOfPaperNeededAfterTest() {
        assertTrue(getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) == 5496)
        assertTrue(getTotalSquareFeetOfPaperNeeded(listOfRectangularPrisms) != 0)
    }

    @Test
    fun getRequiredBowAndRibbonLengthTest() {
        assertTrue(getRequiredBowAndRibbonLength(listOfRectangularPrisms)==14700)
        assertFalse(getRequiredBowAndRibbonLength(listOfRectangularPrisms)==14500)
        assertFalse(getRequiredBowAndRibbonLength(listOfRectangularPrisms)==0)
    }
}