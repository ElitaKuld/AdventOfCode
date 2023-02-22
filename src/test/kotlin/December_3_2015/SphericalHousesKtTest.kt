package December_3_2015

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SphericalHousesKtTest {

    private var gridOfHouses = Array(2) { Array(2) { House() } }
    private val houseA = House(true)
    private val houseB = House() // not visited
    private val dataToWorkWith = "^<v>"
    private var gridOfHouses2 = Array(2) { Array(2) { House() } } // array with non-visited houses
    private val row = 1
    private val column = 1
    private val listOfColumnAndRow = mutableListOf(column,row)

    @Test
    fun countNumberOfVisitedHousesTest() {
        gridOfHouses[0][0] = houseA
        gridOfHouses[0][1] = houseA
        gridOfHouses[1][0] = houseB
        gridOfHouses[1][1] = houseB
        assertTrue(countNumberOfVisitedHouses(gridOfHouses)==2)
        assertFalse(countNumberOfVisitedHouses(gridOfHouses)==4)
    }

    @Test
    fun markHouseAsVisitedTest() {
        for (i in dataToWorkWith.indices) {
            markHouseAsVisited(dataToWorkWith, gridOfHouses2, listOfColumnAndRow,i)
        }
        assertTrue(gridOfHouses2[0][0].visited)
        assertTrue(gridOfHouses2[0][1].visited)
        assertTrue(gridOfHouses2[1][0].visited)
        assertTrue(gridOfHouses2[1][1].visited)
    }

    @Test
    fun getGridWithVisitedHousesTest() {
        assertTrue(getGridWithVisitedHouses(dataToWorkWith, gridOfHouses2, column, row)[0][0].visited)
        assertTrue(getGridWithVisitedHouses(dataToWorkWith, gridOfHouses2, column, row)[0][1].visited)
        assertTrue(getGridWithVisitedHouses(dataToWorkWith, gridOfHouses2, column, row)[1][0].visited)
        assertTrue(getGridWithVisitedHouses(dataToWorkWith, gridOfHouses2, column, row)[1][1].visited)
    }

    @Test
    fun getGridWithVisitedBySantaHousesTest() {
        val gridOfHouses2 = Array(2) { Array(2) { House() } } // array with non-visited houses
        assertTrue(getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHouses2, column, row)[1][0].visited)
        assertTrue(getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHouses2, column, row)[1][1].visited)
        assertFalse(getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHouses2, column, row)[0][0].visited)
        assertFalse(getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHouses2, column, row)[0][1].visited)
    }

    @Test
    fun getGridWithVisitedByRobotHousesTest() {
        val gridOfHouses2 = Array(2) { Array(2) { House() } } // array with non-visited houses
        assertTrue(getGridWithVisitedByRobotHouses(dataToWorkWith, gridOfHouses2, column, row)[0][1].visited)
        assertTrue(getGridWithVisitedByRobotHouses(dataToWorkWith, gridOfHouses2, column, row)[1][1].visited)
        assertTrue(getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHouses2, column, row)[1][0].visited)
        assertFalse(getGridWithVisitedBySantaHouses(dataToWorkWith, gridOfHouses2, column, row)[0][0].visited)
    }
}