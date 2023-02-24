package December_6_2015

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class FireHazardKtTest {

    val filePathname = "src/main/kotlin/December_6_2015/testdata.txt"
    val listOfData = listOf<String>(
        "toggle 461,550 through 564,900",
        "turn off 370,39 through 425,839",
        "turn off 464,858 through 833,915",
        "turn off 812,389 through 865,874",
        "turn on 599,989 through 806,993"
    )
    val gridOfLightsTest = Array(3) { Array(3) { Light() } }
    val instructionTest = Instruction("turn on", 0, 0, 2, 2)
    val instructionTest2 = Instruction("turn off", 0, 0, 2, 2)
    val instructionTest3 = Instruction("toggle", 0, 0, 2, 2)

    @Test
    fun readFileToListTest() {
        assertEquals(readFileToList(filePathname).size, 5)
        assertTrue(readFileToList(filePathname)[0] == "toggle 461,550 through 564,900")
        assertTrue(readFileToList(filePathname)[1] == "turn off 370,39 through 425,839")
        assertTrue(readFileToList(filePathname)[2] == "turn off 464,858 through 833,915")
        assertTrue(readFileToList(filePathname)[3] == "turn off 812,389 through 865,874")
        assertTrue(readFileToList(filePathname)[4] == "turn on 599,989 through 806,993")
    }

    @Test
    fun turnListOfDataIntoListOfInstructionsTest() {
        assertEquals(turnListOfDataIntoListOfInstructions(listOfData).size, 5)
        assertTrue(turnListOfDataIntoListOfInstructions(listOfData)[0].action == "toggle")
        assertTrue(turnListOfDataIntoListOfInstructions(listOfData)[1].columnValueA == 370)
        assertTrue(turnListOfDataIntoListOfInstructions(listOfData)[2].rowValueA == 858)
        assertTrue(turnListOfDataIntoListOfInstructions(listOfData)[3].columnValueB == 865)
        assertTrue(turnListOfDataIntoListOfInstructions(listOfData)[4].rowValueB == 993)
    }

    @Test
    fun setUpLightingTest() {
        setUpLighting(instructionTest, gridOfLightsTest)
        assertTrue(gridOfLightsTest[0][0].lightIsLit)
        assertTrue(gridOfLightsTest[1][1].lightIsLit)
        assertTrue(gridOfLightsTest[2][2].lightIsLit)
        setUpLighting(instructionTest2, gridOfLightsTest)
        assertFalse(gridOfLightsTest[0][0].lightIsLit)
        assertFalse(gridOfLightsTest[1][1].lightIsLit)
        assertFalse(gridOfLightsTest[2][2].lightIsLit)
        setUpLighting(instructionTest3, gridOfLightsTest)
        assertTrue(gridOfLightsTest[0][0].lightIsLit)
        assertTrue(gridOfLightsTest[1][1].lightIsLit)
        assertTrue(gridOfLightsTest[2][2].lightIsLit)
    }

    @Test
    fun changeLevelOfBrightnessTest() {
        changeLevelOfBrightness(instructionTest, gridOfLightsTest)
        assertTrue(gridOfLightsTest[0][0].brightnessLevel==1)
        assertTrue(gridOfLightsTest[1][1].brightnessLevel==1)
        assertTrue(gridOfLightsTest[2][2].brightnessLevel==1)
        changeLevelOfBrightness(instructionTest2, gridOfLightsTest)
        assertTrue(gridOfLightsTest[0][0].brightnessLevel==0)
        assertTrue(gridOfLightsTest[1][1].brightnessLevel==0)
        assertTrue(gridOfLightsTest[2][2].brightnessLevel==0)
        changeLevelOfBrightness(instructionTest3, gridOfLightsTest)
        assertTrue(gridOfLightsTest[0][0].brightnessLevel==2)
        assertTrue(gridOfLightsTest[1][1].brightnessLevel==2)
        assertTrue(gridOfLightsTest[2][2].brightnessLevel==2)
    }

    @Test
    fun turnListOfDataIntoListOfInstructionsAfterTest() {
        assertEquals(turnListOfDataIntoListOfInstructionsAfter(listOfData).size, 5)
        assertTrue(turnListOfDataIntoListOfInstructionsAfter(listOfData)[0].action == "toggle")
        assertTrue(turnListOfDataIntoListOfInstructionsAfter(listOfData)[1].columnValueA == 370)
        assertTrue(turnListOfDataIntoListOfInstructionsAfter(listOfData)[2].rowValueA == 858)
        assertTrue(turnListOfDataIntoListOfInstructionsAfter(listOfData)[3].columnValueB == 865)
        assertTrue(turnListOfDataIntoListOfInstructionsAfter(listOfData)[4].rowValueB == 993)
    }
}